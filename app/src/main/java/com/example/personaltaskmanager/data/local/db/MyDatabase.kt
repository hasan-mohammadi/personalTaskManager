package com.example.personaltaskmanager.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.personaltaskmanager.data.local.db.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)

abstract class MyDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao


    companion object {
        const val DATABASE_NAME = "MyDatabase"
    }
}
