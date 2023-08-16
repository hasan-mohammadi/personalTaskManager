package com.example.personaltaskmanager.di

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    fun provideWorkManager(app: Application): WorkManager =
        WorkManager.getInstance(app.applicationContext)
}