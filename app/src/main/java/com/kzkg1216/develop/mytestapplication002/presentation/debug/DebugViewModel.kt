package com.kzkg1216.develop.mytestapplication002.presentation.debug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzkg1216.develop.mytestapplication002.domain.EditDebugSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebugViewModel @Inject constructor(
    private val editDebugSettingsUseCase: EditDebugSettingsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(DebugUiState.Success.DEFAULT)
    val uiState: StateFlow<DebugUiState> = _uiState

    init {
        getDebugMode()
        getRegisterStatus()
        getLoginStatus()
    }

    fun getDebugMode() {
        viewModelScope.launch {
            editDebugSettingsUseCase.loadDebugMode().collectLatest {
                _uiState.value = (uiState.value as DebugUiState.Success).copy(
                    isDebugging = it
                )
            }
        }
    }

    fun setDebugMode(enable: Boolean) {
        viewModelScope.launch {
            editDebugSettingsUseCase.saveDebugMode(enable)
        }
    }

    fun getRegisterStatus() {
        viewModelScope.launch {
            editDebugSettingsUseCase.loadRegisterStatus().collectLatest {
                _uiState.value = (uiState.value as DebugUiState.Success).copy(
                    isRegistered = it
                )
            }
        }
    }

    fun setRegisterStatus(status: Boolean) {
        viewModelScope.launch {
            editDebugSettingsUseCase.saveRegisterStatus(status)
        }
    }

    fun getLoginStatus() {
        viewModelScope.launch {
            editDebugSettingsUseCase.loadLoginStatus().collectLatest {
                _uiState.value = (uiState.value as DebugUiState.Success).copy(
                    isLoggedIn = it
                )
            }
        }
    }

    fun setLoginStatus(status: Boolean) {
        viewModelScope.launch {
            editDebugSettingsUseCase.saveLoginStatus(status)
        }
    }
}