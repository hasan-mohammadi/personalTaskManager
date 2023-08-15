package com.example.personaltaskmanager.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.data.model.toEntityObject
import com.example.personaltaskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(val repository: TaskRepository):ViewModel() {

    val addTaskResult = MutableStateFlow(false)
fun addOrUpdateTask(task:Task){
    viewModelScope.launch(Dispatchers.IO) {
        repository.addNewTask(task.toEntityObject())
        addTaskResult.emit(true)
    }
}

}
