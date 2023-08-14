package com.example.personaltaskmanager.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0 ,
    val title:String ,
    val description:String ,
    val deadline:Long
)