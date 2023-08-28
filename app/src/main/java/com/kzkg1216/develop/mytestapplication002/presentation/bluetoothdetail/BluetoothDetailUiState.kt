package com.kzkg1216.develop.mytestapplication002.presentation.bluetoothdetail

import com.kzkg1216.develop.mytestapplication002.presentation.bluetooth.LeDevice
import java.lang.Exception

sealed class BluetoothDetailUiState {

    data class Success(
        val devices: List<LeDevice>
    ): BluetoothDetailUiState() {

        companion object {
            val DEFAULT = Success(
                devices = listOf()
            )
        }
    }

    data class Error(
        val exception: Exception
    ): BluetoothDetailUiState()
}