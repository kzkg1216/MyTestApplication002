package com.kzkg1216.develop.mytestapplication002.presentation.welcome

import java.lang.Exception

sealed class WelcomeUiState {
    data class Success(
        val permission: Boolean,
        val isLoading: Boolean,
        val email: String
    ): WelcomeUiState() {

        companion object {
            val DEFAULT = Success(
                permission = false,
                isLoading = false,
                email = ""
            )
        }
    }
    data class Error(
        val exception: Exception
    ): WelcomeUiState()
}

