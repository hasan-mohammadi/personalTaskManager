package com.example.personaltaskmanager.data.remote.response

import com.example.personaltaskmanager.data.local.db.entity.TaskEntity

data class TaskResponse(
    val id:Int ,
    val title:String ,
    val description:String ,
    val deadline:Long ,


)
fun TaskResponse.asDatabaseEntityModel()=
    TaskEntity(id=id , title=title , description = description , deadline = deadline)