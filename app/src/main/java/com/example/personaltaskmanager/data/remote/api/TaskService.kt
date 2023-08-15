package com.example.personaltaskmanager.data.remote.api

import com.example.personaltaskmanager.data.remote.response.TaskResponse
import retrofit2.Response
import retrofit2.http.GET


interface TaskService {

    @GET("fe4abd0f-1221-486b-b736-8f263da0d9bd")
    suspend fun fetchAllTasks(): Response<List<TaskResponse>>

}