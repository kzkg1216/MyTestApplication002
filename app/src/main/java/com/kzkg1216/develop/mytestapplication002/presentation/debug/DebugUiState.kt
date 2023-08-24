package com.kzkg1216.develop.mytestapplication002.presentation.debug

import java.lang.Exception

sealed class DebugUiState {

    data class Success(
        val isLoading: Boolean,
        val isDebugging: Boolean,
        val isRegistered: Boolean,
        val isLoggedIn: Boolean
    ): DebugUiState() {

        companion object {
            val DEFAULT = Success(
                isLoading = false,
                isDebugging = false,
                isRegistered = false,
                isLoggedIn = false
            )
        }
    }

    data class Error(
        val exception: Exception
    ): DebugUiState()
}