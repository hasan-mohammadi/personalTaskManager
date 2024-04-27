package com.example.personaltaskmanager.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.personaltaskmanager.data.model.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ThemeUtil @Inject constructor(@ApplicationContext context: Context) {
    fun setAppTheme(appTheme: AppTheme?){
        when (appTheme) {
            AppTheme.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AppTheme.DAY -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}