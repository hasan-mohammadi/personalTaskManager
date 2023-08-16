package com.example.personaltaskmanager.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    private val _taskListFlow = MutableStateFlow<PagingData<Task>?>(null)
    val taskListFlow = _taskListFlow.asStateFlow()

    init {
        fetchNewTasks()
        getTasks("")
    }


    fun fetchNewTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAllTasks()
            _taskListFlow.emitAll(repository.getTaskList("").cachedIn(viewModelScope))
        }
    }

    fun getTasks(searchQuery:String = ""){
        viewModelScope.launch(Dispatchers.IO) {
            _taskListFlow.emitAll(repository.getTaskList(searchQuery).cachedIn(viewModelScope))
        }
    }




}