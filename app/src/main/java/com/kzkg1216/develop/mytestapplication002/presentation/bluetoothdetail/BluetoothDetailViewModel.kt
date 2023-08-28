package com.kzkg1216.develop.mytestapplication002.presentation.bluetoothdetail

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BluetoothDetailViewModel @Inject constructor(
    private val bluetoothAdapter: BluetoothAdapter
): ViewModel() {

    private val _uiState = MutableStateFlow(BluetoothDetailUiState.Success.DEFAULT)
    val uiState: StateFlow<BluetoothDetailUiState> = _uiState

    private val device by lazy {
        bluetoothAdapter.getRemoteDevice((uiState.value as BluetoothDetailUiState.Success).devices[0].address)
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, value, status)
        }
    }

    @SuppressLint("MissingPermission")
    fun connect(context: Context) {
        device.connectGatt(context, false, gattCallback)
    }
}