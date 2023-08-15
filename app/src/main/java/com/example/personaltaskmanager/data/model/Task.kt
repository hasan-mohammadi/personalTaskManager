package com.example.personaltaskmanager.data.model

import java.text.SimpleDateFormat
import java.util.Date

data class Task(
    val id:Int ,
    val title:String ,
    val description:String ,
    val deadline:Long
) {
    fun getDeadlineDateString(): String? {
        try {
            val sdf = SimpleDateFormat("MM/dd HH:mm")
            val netDate = Date(deadline)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return ""
        }

    }
}
