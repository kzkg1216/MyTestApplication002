package com.kzkg1216.develop.mytestapplication002.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState.Success.DEFAULT)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun updateEmail(email: String) {
        viewModelScope.launch {
            _uiState.value = (uiState.value as LoginUiState.Success).copy(
                email = email
            )
        }
    }

    fun updatePassword(password: String) {
        viewModelScope.launch {
            _uiState.value = (uiState.value as LoginUiState.Success).copy(
                password = password
            )
        }
    }
}