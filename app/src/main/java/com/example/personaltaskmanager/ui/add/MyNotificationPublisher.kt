package com.example.personaltaskmanager.ui.add

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.personaltaskmanager.R
import com.example.personaltaskmanager.utils.NOTIFICATION_DESC_KEY
import com.example.personaltaskmanager.utils.NOTIFICATION_TITLE_KEY
import com.example.personaltaskmanager.utils.NOTIFIER_CHANNEL_ID

class MyNotificationPublisher : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Create a notification
        val notificationBuilder = NotificationCompat.Builder(context, NOTIFIER_CHANNEL_ID)
            .setContentTitle(intent.getStringExtra(NOTIFICATION_TITLE_KEY))
            .setContentText(intent.getStringExtra(NOTIFICATION_DESC_KEY))
            .setSmallIcon(R.drawable.ic_logo)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

    }
}