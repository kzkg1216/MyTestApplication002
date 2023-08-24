package com.kzkg1216.develop.mytestapplication002.data

import androidx.datastore.preferences.core.booleanPreferencesKey

object SettingsPreferencesKeys {
    val DEBUG_MODE_KEY = booleanPreferencesKey("DEBUG_MODE_KEY")
    val REGISTER_STATUS_KEY = booleanPreferencesKey("REGISTER_STATUS_KEY")
    val LOGIN_STATUS_KEY = booleanPreferencesKey("LOGIN_STATUS_KEY")
}