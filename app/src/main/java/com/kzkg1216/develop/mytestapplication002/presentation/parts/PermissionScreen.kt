package com.kzkg1216.develop.mytestapplication002.presentation.parts

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreenState(
    granted: () -> Unit = {  }
) {
    val context = LocalContext.current

    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    when {
        permissionState.allPermissionsGranted -> { granted() }
        permissionState.shouldShowRationale -> {
            Timber.d("shouldShowRationale")
            PermissionScreen(
                text = "Permission should show rationale",
                permissionRequest = { permissionState.launchMultiplePermissionRequest() }
            )
        }
        permissionState.permissionRequested -> {
            Timber.d("permissionRequested")
            PermissionScreen(
                text = "Permission requested",
                permissionRequest = {
                    Timber.d("permissionRequest")
                    val packageName = context.packageName
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.fromParts("package", packageName, null)
                    startActivity(context, intent, null)
                }
            )
        }
        else -> SideEffect {
            Timber.d("launchMultiplePermissionRequest")
            permissionState.launchMultiplePermissionRequest()
        }
    }
}

@Composable
fun PermissionScreen(
    modifier: Modifier = Modifier,
    text: String = "",
    permissionRequest: () -> Unit = {  }
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = text)
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { permissionRequest() }
        ) {
            Text(text = "Permission Request")
        }
    }
}