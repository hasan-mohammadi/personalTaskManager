package com.example.personaltaskmanager.data.remote

import com.example.personaltaskmanager.data.remote.api.TaskService
import com.example.personaltaskmanager.data.remote.response.TaskResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRemoteDataSourceImpl @Inject constructor(private val taskService: TaskService):TaskRemoteDataSource {

    override suspend fun fetchAllTasks(): Response<List<TaskResponse>> {

        return taskService.fetchAllTasks()
    }
}