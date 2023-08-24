package com.kzkg1216.develop.mytestapplication002.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun saveDebugMode(enable: Boolean)
    suspend fun loadDebugMode(): Flow<Boolean>
    suspend fun saveRegisterStatus(status: Boolean)
    suspend fun loadRegisterStatus(): Flow<Boolean>
    suspend fun saveLoginStatus(status: Boolean)
    suspend fun loadLoginStatus(): Flow<Boolean>
}