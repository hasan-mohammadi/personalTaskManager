package com.example.personaltaskmanager.di
import com.example.personaltaskmanager.data.local.TaskLocalDataSource
import com.example.personaltaskmanager.data.local.TaskLocalDataSourceImpl
import com.example.personaltaskmanager.data.remote.TaskRemoteDataSource
import com.example.personaltaskmanager.data.remote.TaskRemoteDataSourceImpl
import com.example.personaltaskmanager.data.repository.TaskRepository
import com.example.personaltaskmanager.data.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindTaskRepository(userRepositoryImpl: TaskRepositoryImpl): TaskRepository
    @Binds
    fun bindTaskLocalDataSource(taskLocalDataSourceImpl: TaskLocalDataSourceImpl): TaskLocalDataSource
    @Binds
    fun bindTaskRemoteDataSource(taskRemoteDataSourceImpl: TaskRemoteDataSourceImpl): TaskRemoteDataSource

}