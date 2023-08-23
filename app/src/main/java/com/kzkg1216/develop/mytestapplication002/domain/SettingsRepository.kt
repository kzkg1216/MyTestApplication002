package com.kzkg1216.develop.mytestapplication002.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun saveDebugMode(enable: Boolean)
    suspend fun loadDebugMode(): Flow<Boolean>
}