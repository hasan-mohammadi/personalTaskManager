package com.example.personaltaskmanager.utils

import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


object PermissionManager {
    fun requestNotificationPermissionIfNeeded(
        activity: AppCompatActivity,
        onPermissionResult: (isGranted: Boolean) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) if (ContextCompat.checkSelfPermission(
                activity, android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                onPermissionResult.invoke(result)
            }.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
