package com.kzkg1216.develop.mytestapplication002.presentation.bluetooth

import android.bluetooth.BluetoothDevice

data class LeDevice(
    val device: BluetoothDevice,
    val name: String,
    val address: String,
    val rssi: String
)
