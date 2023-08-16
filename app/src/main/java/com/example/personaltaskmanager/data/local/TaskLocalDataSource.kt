package com.example.personaltaskmanager.data.local

import com.example.personaltaskmanager.data.local.db.entity.TaskEntity
import com.example.personaltaskmanager.data.model.Task

interface TaskLocalDataSource {
    suspend fun getAllTasks(pageSize: Int, offset: Int, titleSearchQuery: String): List<Task>
    suspend fun insertTasks(taskEntities: List<TaskEntity>)
    suspend fun insertTask(taskEntity: TaskEntity):Long
    suspend fun deleteTaskById(id: Int)

}