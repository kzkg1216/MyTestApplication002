package com.kzkg1216.develop.mytestapplication002.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState.Success.DEFAULT)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun updateEmail(email: String) {
        viewModelScope.launch {
            _uiState.value = (uiState.value as RegisterUiState.Success).copy(
                email = email
            )
        }
    }

    fun updatePassword(password: String) {
        viewModelScope.launch {
            _uiState.value = (uiState.value as RegisterUiState.Success).copy(
                password = password
            )
        }
    }
}