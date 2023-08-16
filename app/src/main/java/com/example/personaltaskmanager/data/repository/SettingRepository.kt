package com.example.personaltaskmanager.data.repository

import androidx.paging.PagingData
import com.example.personaltaskmanager.data.local.db.entity.TaskEntity
import com.example.personaltaskmanager.data.model.AppTheme
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

     fun getAppTheme():Flow<AppTheme>
     suspend fun setAppTheme(appTheme: AppTheme)
}