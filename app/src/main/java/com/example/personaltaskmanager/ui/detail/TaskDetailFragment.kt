package com.example.personaltaskmanager.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.personaltaskmanager.R
import com.example.personaltaskmanager.databinding.FragmentTaskDetailBinding
import com.example.personaltaskmanager.utils.collectFlowAtLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailFragment : Fragment() {
    lateinit var binding: FragmentTaskDetailBinding
    private val viewModel: TaskDetailViewModel by viewModels()
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateRemainingTime(args.task.getDeadlineDate())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() {

        val task = args.task
        binding.apply {
            tvTaskTitle.text = task.title
            tvTaskDesc.text = task.description
            tvDeadlineTime.text = task.getDeadlineDateString()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        collectFlowAtLifecycle(
            viewModel.countDownTimerFlow(1000, args.task.getDeadlineDate())
        ) { remainingTime ->
            if (remainingTime==null){
                binding.tvRemainingTime.text=getString(R.string.no_time_remained)
                return@collectFlowAtLifecycle
            }
            binding.tvRemainingTime.text = String.format(
                "%s days and %02d:%02d:%02d",
                remainingTime.day,
                remainingTime.hour,
                remainingTime.minute,
                remainingTime.second,
            )



        }
    }


}

