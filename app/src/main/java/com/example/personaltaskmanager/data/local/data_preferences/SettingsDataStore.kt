package com.example.personaltaskmanager.data.local.data_preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.personaltaskmanager.data.model.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val PREFERENCES_NAME_USER = "PREF_DATA_SOURCE1"

private val Context.prefsDataStore by preferencesDataStore(
    name = PREFERENCES_NAME_USER
)

class SettingsDataStore @Inject constructor(val context: Context) {


    suspend fun  setAppTheme(theme : AppTheme) {
        context.prefsDataStore.edit { preferences ->
            preferences[PreferenceKeys.APP_THEME] =theme.name
        }
    }


     fun  getAppTheme(): Flow<AppTheme> {
        return context.prefsDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                AppTheme.valueOf(preferences[PreferenceKeys.APP_THEME] ?: "DEFAULT")

            }.distinctUntilChanged()
    }

}