package com.example.personaltaskmanager.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personaltaskmanager.data.model.AppTheme
import com.example.personaltaskmanager.data.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SettingsViewModel @Inject constructor(
    val repository: SettingRepository
):ViewModel() {
    val appTheme = repository.getAppTheme()

    fun setAppTheme(appTheme: AppTheme){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setAppTheme(appTheme)
        }

    }
}