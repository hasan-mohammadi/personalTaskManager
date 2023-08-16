package com.example.personaltaskmanager.ui

import androidx.lifecycle.ViewModel
import com.example.personaltaskmanager.data.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: SettingRepository) : ViewModel() {

    val appTheme = repository.getAppTheme()

}