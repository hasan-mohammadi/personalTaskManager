package com.example.personaltaskmanager.data.remote

import com.example.personaltaskmanager.data.remote.response.TaskResponse
import retrofit2.Response

interface TaskRemoteDataSource {
    suspend fun fetchAllTasks(): Response<List<TaskResponse>>
}