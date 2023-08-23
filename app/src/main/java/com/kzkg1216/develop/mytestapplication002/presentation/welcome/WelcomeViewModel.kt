package com.kzkg1216.develop.mytestapplication002.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.Permission
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(WelcomeUiState.Success.DEFAULT)
    val uiState: StateFlow<WelcomeUiState> = _uiState

    init {
        viewModelScope.launch {

        }
    }

    fun setUiStatePermission(permission: Boolean) {
        _uiState.value = (uiState.value as WelcomeUiState.Success).copy(
            permission = permission
        )
    }

    fun register() {
        viewModelScope.launch {

        }
    }

    fun login() {
        viewModelScope.launch {

        }
    }
}