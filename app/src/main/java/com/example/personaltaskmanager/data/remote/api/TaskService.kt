package com.example.personaltaskmanager.data.remote.api

import com.example.personaltaskmanager.data.remote.response.TaskResponse
import retrofit2.Response
import retrofit2.http.GET


interface TaskService {

    @GET("04ed5f15-a80c-4164-b511-3c3091a77140")
    suspend fun fetchAllTasks(): Response<List<TaskResponse>>

}