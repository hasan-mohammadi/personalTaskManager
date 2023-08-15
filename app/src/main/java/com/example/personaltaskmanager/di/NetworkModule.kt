package com.example.personaltaskmanager.di

import com.example.personaltaskmanager.data.remote.api.TaskService
import com.example.personaltaskmanager.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideTaskService(
        okHttpClient: OkHttpClient ,
        convertorFactory:GsonConverterFactory
    ): TaskService {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(convertorFactory)
            .build()
            .create(TaskService::class.java)
    }


    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
    @Provides
    fun provideConvertorFactory(
    ): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}

