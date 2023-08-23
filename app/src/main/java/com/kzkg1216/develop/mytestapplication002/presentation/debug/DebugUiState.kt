package com.kzkg1216.develop.mytestapplication002.presentation.debug

import java.lang.Exception

sealed class DebugUiState {

    data class Success(
        val isLoading: Boolean,
        val isDebugging: Boolean
    ): DebugUiState() {

        companion object {
            val DEFAULT = Success(
                isLoading = false,
                isDebugging = false
            )
        }
    }

    data class Error(
        val exception: Exception
    ): DebugUiState()
}