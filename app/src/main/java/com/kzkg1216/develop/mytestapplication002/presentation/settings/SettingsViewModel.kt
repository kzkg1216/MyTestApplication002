package com.kzkg1216.develop.mytestapplication002.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzkg1216.develop.mytestapplication002.domain.EditDebugSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val editDebugSettingsUseCase: EditDebugSettingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState.Success.DEFAULT)
    val uiState: StateFlow<SettingsUiState> = _uiState

    init {
        viewModelScope.launch {
            getDebugMode()
        }
    }

    fun setDebugMode(enable: Boolean) {
        viewModelScope.launch {
            editDebugSettingsUseCase.saveDebugMode(enable)
        }
    }

    fun getDebugMode() {
        viewModelScope.launch {
            editDebugSettingsUseCase.loadDebugMode().collectLatest {
                _uiState.value = (uiState.value as SettingsUiState.Success).copy(
                    isDebugging = it
                )
            }
        }
    }
}