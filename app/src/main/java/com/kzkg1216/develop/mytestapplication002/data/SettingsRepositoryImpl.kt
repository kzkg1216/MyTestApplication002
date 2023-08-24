package com.kzkg1216.develop.mytestapplication002.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.kzkg1216.develop.mytestapplication002.domain.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl constructor(
    private val dataStore: DataStore<Preferences>
): SettingsRepository {

    override suspend fun saveDebugMode(enable: Boolean) {
        dataStore.edit { preferences ->
            preferences[SettingsPreferencesKeys.DEBUG_MODE_KEY] = enable
        }
    }

    override suspend fun loadDebugMode(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[SettingsPreferencesKeys.DEBUG_MODE_KEY] ?: false
        }
    }

    override suspend fun saveRegisterStatus(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[SettingsPreferencesKeys.REGISTER_STATUS_KEY] = status
        }
    }

    override suspend fun loadRegisterStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[SettingsPreferencesKeys.REGISTER_STATUS_KEY] ?: false
        }
    }

    override suspend fun saveLoginStatus(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[SettingsPreferencesKeys.LOGIN_STATUS_KEY] = status
        }
    }

    override suspend fun loadLoginStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[SettingsPreferencesKeys.LOGIN_STATUS_KEY] ?: false
        }
    }


}