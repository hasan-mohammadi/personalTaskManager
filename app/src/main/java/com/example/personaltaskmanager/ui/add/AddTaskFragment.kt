package com.example.personaltaskmanager.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personaltaskmanager.R
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.databinding.FragmentAddTaskBinding
import com.example.personaltaskmanager.utils.collectFlowAtLifecycle
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    lateinit var binding: FragmentAddTaskBinding
    val viewModel: AddTaskViewModel by viewModels()
    val args: AddTaskFragmentArgs by navArgs()
    lateinit var task: Task
    val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    }
    val timePicker by lazy {
        MaterialTimePicker.Builder()
            .setTitleText("Select date")
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
    }
    var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task = args.task ?: Task(0, "", "", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        args.task?.let {
            isEditMode = true
            binding.inputTitle.setText(it.title)
            binding.inputDescription.setText(it.description)
            binding.btnDeadline.text = it.getDeadlineDateString()
            binding.btnAdd.text = getString(R.string.edit_task)
        }

        binding.btnDeadline.setOnClickListener {
            datePicker.show(parentFragmentManager, "date_picker")
        }
        binding.btnAdd.setOnClickListener {
            if (task.deadline <= 0) {
                Toast.makeText(
                    requireContext(),
                    "Please specify Deadline for task",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            task = task.copy(
                title = binding.inputTitle.text.toString(),
                description = binding.inputDescription.text.toString()
            )
            viewModel.addOrUpdateTask(task)
        }
        datePicker.addOnPositiveButtonClickListener { timestamp ->
            timePicker.show(parentFragmentManager, "time_picker")
            selectedDateTimestamp = timestamp
        }
        timePicker.addOnPositiveButtonClickListener {
            val date = Date(selectedDateTimestamp)
            date.hours = timePicker.hour
            date.minutes = timePicker.minute
            task = task.copy(deadline = date.time)
            binding.btnDeadline.text = task.getDeadlineDateString()
        }
    }

    var selectedDateTimestamp = 0L
    private fun setupObservers() {
        collectFlowAtLifecycle(viewModel.addTaskResult) { isAdded ->
            if (isAdded == true) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.add_task_dialog_title))
                    .setMessage(resources.getString(R.string.add_task_dialog_desc))
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        if (!isEditMode)
                        findNavController().popBackStack()
                        else {
                            findNavController().navigate(AddTaskFragmentDirections.actionAddTaskFragmentToTaskDetailFragment2(task))
                        }
                    }
                    .show()
            }
        }

    }

}