package com.example.personaltaskmanager.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.personaltaskmanager.ui.add.MyNotificationPublisher

const val NOTIFICATION_TITLE_KEY = "notification_title"
const val NOTIFICATION_DESC_KEY = "notification_desc"
const val NOTIFIER_CHANNEL_ID = "notifier_channel_id"

class NotificationManager(val activity: Activity) {

    val CHANNEL_Name = "channel_name"

    init {
        createNotificationChannel()
    }

    fun setupScheduledNotification(
        timeMillis: Long,
        title: String,
        description: String,
        requestCode: Int
    ) {
        createNotificationChannel()
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent =
            Intent(activity.applicationContext, MyNotificationPublisher::class.java)
        intent.putExtra(NOTIFICATION_TITLE_KEY, title)
        intent.putExtra(NOTIFICATION_DESC_KEY, description)


        val pendingIntent = PendingIntent.getBroadcast(
            activity.applicationContext,
            requestCode,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                PendingIntent.FLAG_IMMUTABLE
            else PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP, timeMillis, pendingIntent
        )

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(NOTIFIER_CHANNEL_ID, CHANNEL_Name, importance).apply {
                description = "channel description"
            }


            val notificationManager: NotificationManager =
                activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun cancelNotification(requestCode: Int) {
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent =
            Intent(activity.applicationContext, MyNotificationPublisher::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            activity.applicationContext, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.cancel(pendingIntent)
    }

}