package com.kzkg1216.develop.mytestapplication002.presentation.settings

import java.lang.Exception

sealed class SettingsUiState {
    data class Success(
        val isLoading: Boolean,
        val isDebugging: Boolean
    ): SettingsUiState() {

        companion object {
            val DEFAULT = Success(
                isLoading = true,
                isDebugging = false
            )
        }
    }
    data class Error(
        val exception: Exception
    ): SettingsUiState()
}

