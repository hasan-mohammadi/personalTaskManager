package com.example.personaltaskmanager.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.personaltaskmanager.data.local.db.entity.TaskEntity

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(tasks:List<TaskEntity>)
    @Query("delete from TaskEntity where id=:id")
    suspend fun deleteTask(id:Int)
@Query("Select * from TaskEntity where title like '%' || :searchQuery || '%' limit :pageSize offset :offset")
    suspend fun getAllTasks(pageSize:Int , offset:Int , searchQuery:String):List<TaskEntity>


}