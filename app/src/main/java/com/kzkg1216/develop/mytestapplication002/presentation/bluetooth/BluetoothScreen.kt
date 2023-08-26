package com.kzkg1216.develop.mytestapplication002.presentation.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import timber.log.Timber

@Composable
fun BluetoothScreenState(
    viewModel: BluetoothViewModel,
    navigateToWelcome: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    val enableBtLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        Timber.d("${activityResult.data}")
    }

    BluetoothScreen(
        state = state.value as BluetoothUiState.Success,
        scrollState = scrollState,
        checkBluetooth = {
            viewModel.checkBluetooth(
                launcher = { enableBtLauncher.launch(it) }
            )
        },
        startScanLeDevices = {
            viewModel.startScanLeDevices()
        },
        stopScanLeDevices = {
            viewModel.stopScanLeDevices()
        },
        navigateToWelcome = navigateToWelcome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BluetoothScreen(
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(top = 8.dp),
    state: BluetoothUiState.Success = BluetoothUiState.Success.DEFAULT,
    scrollState: ScrollState = ScrollState(0),
    navigateToWelcome: () -> Unit = {  },
    checkBluetooth: () -> Unit = {  },
    startScanLeDevices: () -> Unit = {  },
    stopScanLeDevices: () -> Unit = {  }
) {

    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            item {
                Column(
                    modifier = modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Button(
                        modifier = buttonModifier,
                        onClick = { navigateToWelcome() }
                    ) {
                        Text(text = "Welcome")
                    }

                    Button(
                        modifier = buttonModifier,
                        onClick = { checkBluetooth() }
                    ) {
                        Text(text = "Check bluetooth")
                    }

                    Button(
                        modifier = buttonModifier,
                        onClick = { startScanLeDevices() }
                    ) {
                        Text(text = "Start scan devices")
                    }

                    Button(
                        modifier = buttonModifier,
                        onClick = { stopScanLeDevices() }
                    ) {
                        Text(text = "Stop scan devices")
                    }
                }
            }
            val devicesSize = state.devices.size
            items(devicesSize) { index ->
                Column(
                    modifier = modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Text(text = "name: ${state.devices[index].name}")
                    Text(text = "address: ${state.devices[index].address}")
                    Text(text = "rssi: ${state.devices[index].rssi}")
                }
            }
        }
    }
}