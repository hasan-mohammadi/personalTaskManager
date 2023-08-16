package com.example.personaltaskmanager.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.personaltaskmanager.data.local.TaskLocalDataSource
import com.example.personaltaskmanager.data.local.db.entity.TaskEntity
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.data.remote.TaskRemoteDataSource
import com.example.personaltaskmanager.data.remote.response.asDatabaseEntityModel
import com.example.personaltaskmanager.utils.GenericPagingSource
import com.example.personaltaskmanager.utils.Resource
import com.example.personaltaskmanager.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource
) : TaskRepository {


    override suspend fun fetchAllTasks(): Resource<Unit> {
        val response = safeApiCall {
            remoteDataSource.fetchAllTasks()
        }

        return when (response) {
            is Resource.Success -> {
                localDataSource.insertTasks(response.data.map {
                    it.asDatabaseEntityModel()
                })
                Resource.Success(Unit)
            }

            is Resource.Error -> response
            else -> Resource.Loading()
        }

    }

    override  fun getTaskList(searchQuery: String): Flow<PagingData<Task>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                maxSize = 30
            ),
            pagingSourceFactory = {
                GenericPagingSource { pageSize, offset ->
                    localDataSource.getAllTasks(pageSize, offset, searchQuery)
                }
            }
        ).flow
    }
    override suspend fun addNewTasks(taskEntity: TaskEntity): Long {
        return localDataSource.insertTask(taskEntity)
    }

    override suspend fun deleteTask(taskId:Int) {
        localDataSource.deleteTaskById(taskId)
    }


}