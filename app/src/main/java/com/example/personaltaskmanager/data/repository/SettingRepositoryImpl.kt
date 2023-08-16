package com.example.personaltaskmanager.data.repository

import com.example.personaltaskmanager.data.local.data_preferences.SettingsDataStore
import com.example.personaltaskmanager.data.model.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(private val settingDataStore:SettingsDataStore):SettingRepository {

     override  fun getAppTheme(): Flow<AppTheme> {
          return settingDataStore.getAppTheme()
     }

     override suspend fun setAppTheme(appTheme: AppTheme) {
          settingDataStore.setAppTheme(appTheme)
     }
}