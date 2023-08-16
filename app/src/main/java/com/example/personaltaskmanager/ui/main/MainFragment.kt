package com.example.personaltaskmanager.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personaltaskmanager.databinding.FragmentMainBinding
import com.example.personaltaskmanager.utils.collectFlowAtLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val taskAdapter: TaskAdapter by lazy {
        TaskAdapter {
            it?.let { task ->
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToTaskDetailFragment22(
                        task
                    )
                )
            }
        }
    }
    private val viewModel: TaskListViewModel by viewModels()
    private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }

        binding.inputSearch.addTextChangedListener {
            searchJob = lifecycleScope.launch {
                searchJob?.cancel()
                delay(100)
                viewModel.getTasks(it.toString())
            }
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToAddTaskFragment(
                    null
                )
            )
        }
        binding.ivSettings.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSettingsFragment())
        }
    }

    private fun setupObservers() {
        collectFlowAtLifecycle(viewModel.taskListFlow) { tasks ->
            tasks?.let {
                taskAdapter.submitData(it)
            }

        }
    }

}