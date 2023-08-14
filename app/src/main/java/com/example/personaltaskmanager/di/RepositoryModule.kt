package com.example.personaltaskmanager.di

import com.example.personaltaskmanager.data.remote.api.TaskService
import com.example.personaltaskmanager.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideTaskService(
        okHttpClient: OkHttpClient
    ): TaskService {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .client(okHttpClient)
            .build()
            .create(TaskService::class.java)
    }


    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}

