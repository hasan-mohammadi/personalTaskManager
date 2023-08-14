package com.example.personaltaskmanager.di

import android.app.Application
import androidx.room.Room
import com.example.personaltaskmanager.data.local.db.MyDatabase
import com.example.personaltaskmanager.data.local.db.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): MyDatabase {
        return Room.databaseBuilder(
            app,
            MyDatabase::class.java, MyDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideVideoItemDao(db:MyDatabase): TaskDao {
        return db.taskDao()
    }
}