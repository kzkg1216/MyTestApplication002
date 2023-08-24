package com.kzkg1216.develop.mytestapplication002.presentation.register

import java.lang.Exception

sealed class RegisterUiState {

    data class Success(
        val email: String,
        val password: String
    ): RegisterUiState() {

        companion object {
            val DEFAULT = Success(
                email = "",
                password = ""
            )
        }
    }

    data class Error(
        val exception: Exception
    ): RegisterUiState()
}
