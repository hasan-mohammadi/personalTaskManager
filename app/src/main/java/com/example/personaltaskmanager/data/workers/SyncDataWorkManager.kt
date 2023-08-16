package com.example.personaltaskmanager.data.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.personaltaskmanager.data.repository.TaskRepository
import com.example.personaltaskmanager.utils.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class SyncDataWorkManager @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val repository: TaskRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d("WORKMANAGER_LOG", "res.toString()")
        val res = repository.fetchAllTasks()
        return@withContext when (res) {
            is Resource.Error -> Result.failure()
            is Resource.Loading -> Result.success()
            is Resource.Success -> Result.success()
        }

    }
}