package com.example.personaltaskmanager.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.personaltaskmanager.data.model.AppTheme
import com.example.personaltaskmanager.databinding.FragmentSettingsBinding
import com.example.personaltaskmanager.utils.collectFlowAtLifecycle
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : DialogFragment() {
lateinit var binding:FragmentSettingsBinding
val viewModel : SettingsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupViews() {
        binding.apply {
            chipNight.setOnClickListener { viewModel.setAppTheme(AppTheme.NIGHT) }
            chipDay.setOnClickListener { viewModel.setAppTheme(AppTheme.DAY) }
            chipDefault.setOnClickListener { viewModel.setAppTheme(AppTheme.DEFAULT) }
        }
    }

    fun setupObservers(){
        collectFlowAtLifecycle(viewModel.appTheme){
            binding.chipgroupTheme.children.forEach {
                (it as Chip).isChecked=false
            }
            when(it){
                AppTheme.DAY -> binding.chipDay.isCheckable=true
                AppTheme.NIGHT -> binding.chipNight.isCheckable=true
                AppTheme.DEFAULT -> binding.chipDefault.isCheckable=true
                null -> binding.chipDefault.isCheckable=true
            }
        }

    }



}