package com.example.personaltaskmanager.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.personaltaskmanager.data.model.Task

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0 ,
    val title:String ,
    val description:String ,
    val deadline:Long
)

fun TaskEntity.asUiModel()=
    Task(id , title , description, deadline)