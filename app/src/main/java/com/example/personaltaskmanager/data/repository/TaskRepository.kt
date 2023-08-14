package com.example.personaltaskmanager.data.repository

import androidx.paging.PagingData
import com.example.personaltaskmanager.data.local.db.entity.TaskEntity
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getTaskList(searchQuery:String): Flow<PagingData<Task>>
    suspend fun fetchAllTasks():Resource<Unit>
    suspend fun addNewTask(taskEntity: TaskEntity)
    suspend fun deleteTask(taskId:Int)
}