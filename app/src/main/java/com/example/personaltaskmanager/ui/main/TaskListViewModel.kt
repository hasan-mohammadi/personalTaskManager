package com.example.personaltaskmanager.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.data.repository.TaskRepository
import com.example.personaltaskmanager.data.workers.SyncDataWorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository,
    val workManager: WorkManager
) : ViewModel() {
    private val _taskListFlow = MutableStateFlow<PagingData<Task>?>(null)
    val taskListFlow = _taskListFlow.asStateFlow()

    init {
        fetchNewTasks()
        schedulePeriodicApiCall()
        getTasks("")
    }


    fun fetchNewTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAllTasks()
            _taskListFlow.emitAll(repository.getTaskList("").cachedIn(viewModelScope))
        }
    }

    fun getTasks(searchQuery: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            _taskListFlow.emitAll(repository.getTaskList(searchQuery).cachedIn(viewModelScope))
        }
    }

    fun schedulePeriodicApiCall() {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val periodicWorkRequest =
                PeriodicWorkRequest.Builder(
                    SyncDataWorkManager::class.java,
                    15,
                    TimeUnit.MINUTES,
                    5,
                    TimeUnit.MINUTES
                )
                    .setConstraints(constraints)
                    .setInitialDelay(1, TimeUnit.MINUTES)
                    .build()

            workManager.enqueueUniquePeriodicWork(
                "sync_data_work",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )

    }


}