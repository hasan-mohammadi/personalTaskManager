package com.example.personaltaskmanager.utils

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.personaltaskmanager.data.repository.TaskRepository
import com.example.personaltaskmanager.data.workers.SyncDataWorkManager
import javax.inject.Inject

class SyncWorkerFactory @Inject constructor(val repository: TaskRepository) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return  if (workerClassName == SyncDataWorkManager::class.qualifiedName) {
            SyncDataWorkManager(appContext, workerParameters, repository)
        } else  null
    }


}