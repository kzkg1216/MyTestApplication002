package com.kzkg1216.develop.mytestapplication002.presentation.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kzkg1216.develop.mytestapplication002.presentation.parts.LoadingScreen
import com.kzkg1216.develop.mytestapplication002.presentation.parts.PermissionScreenState

@Composable
fun WelcomeScreenState(
    viewModel: WelcomeViewModel,
    navigateToDebug: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    if (state.value is WelcomeUiState.Success) {

        if (!(state.value as WelcomeUiState.Success).permission) {

            PermissionScreenState(
                granted = { viewModel.setUiStatePermission(true) }
            )
        } else {

            WelcomeScreen(
                state = state.value as WelcomeUiState.Success,
                navigateToDebug = navigateToDebug,
                navigateToSettings = navigateToSettings,
                navigateToRegister = navigateToRegister,
                navigateToLogin = navigateToLogin
            )
        }
    }
}

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    state: WelcomeUiState.Success = WelcomeUiState.Success.DEFAULT,
    navigateToDebug: () -> Unit = {  },
    navigateToSettings: () -> Unit = {  },
    navigateToRegister: () -> Unit = {  },
    navigateToLogin: () -> Unit = {  }
) {

    if (!state.isLoading) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { navigateToRegister() }
            ) {

                Text("Register")
            }

            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { navigateToLogin() }
            ) {

                Text("Login")
            }

            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { navigateToDebug() }
            ) {

                Text("Debug")
            }

            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { navigateToSettings() }
            ) {

                Text("Settings")
            }
        }
    } else {
        LoadingScreen()
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}
