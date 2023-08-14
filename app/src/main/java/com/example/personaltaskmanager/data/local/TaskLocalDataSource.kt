package com.example.personaltaskmanager.data.local

import com.example.personaltaskmanager.data.local.db.entity.TaskEntity
import com.example.personaltaskmanager.data.model.Task

interface TaskLocalDataSource {
    suspend fun getAllTasks(pageSize: Int, offset: Int, titleSearchQuery: String): List<Task>
    suspend fun insertTask(taskEntities: List<TaskEntity>)
    suspend fun deleteTaskById(id: Int)

}