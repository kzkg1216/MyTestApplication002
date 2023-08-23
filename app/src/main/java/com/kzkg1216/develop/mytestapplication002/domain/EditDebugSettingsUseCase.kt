package com.kzkg1216.develop.mytestapplication002.domain

import kotlinx.coroutines.flow.Flow

class EditDebugSettingsUseCase constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend fun loadDebugMode(): Flow<Boolean> {
        return settingsRepository.loadDebugMode()
    }

    suspend fun saveDebugMode(enable: Boolean) {
        settingsRepository.saveDebugMode(enable)
    }
}