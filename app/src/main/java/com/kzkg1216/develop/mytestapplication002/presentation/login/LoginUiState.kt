package com.kzkg1216.develop.mytestapplication002.presentation.login

import java.lang.Exception

sealed class LoginUiState {
    data class Success(
        val email: String,
        val password: String
    ): LoginUiState() {

        companion object {
            val DEFAULT = Success(
                email = "",
                password = ""
            )
        }
    }

    data class Error(
        val exception: Exception
    ): LoginUiState()
}
