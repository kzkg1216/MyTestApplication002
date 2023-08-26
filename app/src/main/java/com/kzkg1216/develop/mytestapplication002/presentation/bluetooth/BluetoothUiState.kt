package com.kzkg1216.develop.mytestapplication002.presentation.bluetooth

import java.lang.Exception

sealed class BluetoothUiState {

    data class Success(
        val devices: List<LeDevice>
    ): BluetoothUiState() {

        companion object {
            val DEFAULT = Success(
                devices = listOf()
            )
        }
    }

    data class Error(
        val exception: Exception
    ): BluetoothUiState()
}