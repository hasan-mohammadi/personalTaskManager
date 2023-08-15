package com.example.personaltaskmanager.data.model

import android.os.Parcelable
import com.example.personaltaskmanager.data.local.db.entity.TaskEntity
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Parcelize
data class Task(
    val id:Int ,
    val title:String ,
    val description:String ,
    val deadline:Long
):Parcelable {
    fun getDeadlineDateString(): String? {
        return try {
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm" , Locale.getDefault())
            val netDate = Date(deadline)
            sdf.format(netDate)
        } catch (e: Exception) {
            ""
        }

    }
}
fun Task.toEntityObject()=
    TaskEntity(id, title, description, deadline)