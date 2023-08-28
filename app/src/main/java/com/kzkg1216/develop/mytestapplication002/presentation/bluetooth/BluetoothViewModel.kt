package com.kzkg1216.develop.mytestapplication002.presentation.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothAdapter: BluetoothAdapter
): ViewModel() {

    private val _uiState = MutableStateFlow(BluetoothUiState.Success.DEFAULT)
    val uiState: StateFlow<BluetoothUiState> = _uiState

    private val BluetoothAdapter.isDisabled: Boolean
        get() = !isEnabled

    private val bluetoothLeScanner by lazy {
        bluetoothAdapter.bluetoothLeScanner
    }

    fun checkBluetooth(
        launcher: (intent: Intent) -> Unit
    ) {
        bluetoothAdapter.takeIf { it.isDisabled }.apply {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            launcher(enableBtIntent)
        }
    }

    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
        .setMatchMode(ScanSettings.MATCH_MODE_STICKY)
        .build()

    private val scanFilters = mutableListOf<ScanFilter>()

    private val leScanCallback = object: ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)

            val device = result?.device
            val rssi = result?.rssi
            val address = device?.address
            val name = device?.name
            val scanRecord = result?.scanRecord?.bytes

            Timber.d("callbackType: $callbackType")
            Timber.d("device: $device")
            Timber.d("rssi: $rssi")
            Timber.d("name: $name")
            Timber.d("address: $address")

            scanRecord?.let { bytes ->
                if (bytes.isNotEmpty()) {
                    val advertisementType = bytes[0].toInt() and 0xFF
                    println("Advertisement Type: $advertisementType")
                }
            }

            device?.let { _ ->
                val leDevice = LeDevice(
                    name = name ?: "N/A",
                    address = address ?: "",
                    rssi = rssi.toString()
                )

                viewModelScope.launch {
                    var devices = (uiState.value as BluetoothUiState.Success).devices
                    val index = devices.indexOfFirst { it.address == leDevice.address }
                    if (index != -1) {
                        val updateDevices = devices.map { item ->
                            if (item.address == leDevice.address) {
                                leDevice
                            } else {
                                item
                            }
                        }
                        devices = updateDevices
                    } else {
                        devices = devices.plus(leDevice)
                    }
                    _uiState.value = (uiState.value as BluetoothUiState.Success).copy(
                        devices = devices
                    )
                }
            }
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
        }
    }

    @SuppressLint("MissingPermission")
    fun startScanLeDevices(toast: Toast) {
        if (bluetoothAdapter.isDisabled) {
            Timber.d("Bluetooth OFF")
            toast.show()
            return
        }

        if (!(uiState.value as BluetoothUiState.Success).isScanning) {
            viewModelScope.launch {
                Timber.d("LeScanner Start")
                _uiState.value = (uiState.value as BluetoothUiState.Success).copy(isScanning = true)
                bluetoothLeScanner?.startScan(scanFilters, scanSettings, leScanCallback)
                delay(5000)
                Timber.d("LeScanner Stop")
                bluetoothLeScanner?.stopScan(leScanCallback)
                _uiState.value = (uiState.value as BluetoothUiState.Success).copy(isScanning = false)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun stopScanLeDevices(toast: Toast) {
        if (bluetoothAdapter.isDisabled) {
            Timber.d("Bluetooth OFF")
            toast.show()
            return
        }

        if ((uiState.value as BluetoothUiState.Success).isScanning && !bluetoothAdapter.isDisabled) {
            viewModelScope.launch {
                Timber.d("LeScanner Stop")
                bluetoothLeScanner?.stopScan(leScanCallback)
                _uiState.value = (uiState.value as BluetoothUiState.Success).copy(isScanning = false)
            }
        }
    }
}