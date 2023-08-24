package com.kzkg1216.develop.mytestapplication002.presentation.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(top = 8.dp),
    state: WelcomeUiState.Success = WelcomeUiState.Success.DEFAULT,
    navigateToDebug: () -> Unit = {  },
    navigateToSettings: () -> Unit = {  },
    navigateToRegister: () -> Unit = {  },
    navigateToLogin: () -> Unit = {  }
) {

    if (!state.isLoading) {

        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    modifier = modifier,
                    onClick = { navigateToRegister() }
                ) {

                    Text("Register")
                }

                Button(
                    modifier = modifier,
                    onClick = { navigateToLogin() }
                ) {

                    Text("Login")
                }

                Button(
                    modifier = modifier,
                    onClick = { navigateToDebug() }
                ) {

                    Text("Debug")
                }

                Button(
                    modifier = modifier,
                    onClick = { navigateToSettings() }
                ) {

                    Text("Settings")
                }
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
