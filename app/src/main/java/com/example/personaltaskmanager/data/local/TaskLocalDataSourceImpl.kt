package com.example.personaltaskmanager.data.local

import com.example.personaltaskmanager.data.local.db.TaskDao
import com.example.personaltaskmanager.data.local.db.entity.TaskEntity
import com.example.personaltaskmanager.data.local.db.entity.asUiModel
import com.example.personaltaskmanager.data.model.Task
import javax.inject.Inject

class TaskLocalDataSourceImpl @Inject constructor(private val taskDao:TaskDao):TaskLocalDataSource {

     override suspend fun getAllTasks(pageSize:Int , offset:Int , titleSearchQuery:String): List<Task> {
         return taskDao.getAllTasks(pageSize, offset , titleSearchQuery).map {
             it.asUiModel()
         }

    }
    override suspend fun insertTasks(taskEntities: List<TaskEntity>) {
         taskDao.insertTask(taskEntities)
    }

    override suspend fun insertTask(taskEntity: TaskEntity): Long {
        return taskDao.insertTask(taskEntity)
    }

    override suspend fun deleteTaskById(id:Int){
        taskDao.deleteTask(id)
    }
}