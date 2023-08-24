package com.kzkg1216.develop.mytestapplication002.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreenState(
    viewModel: LoginViewModel,
    navigateToWelcome: () -> Unit,
    navigateToRegister: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        state = state.value as LoginUiState.Success,
        updateEmail = { viewModel.updateEmail(it) },
        updatePassword = { viewModel.updatePassword(it) },
        navigateToWelcome = navigateToWelcome,
        navigateToRegister = navigateToRegister
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(top = 8.dp),
    state: LoginUiState.Success = LoginUiState.Success.DEFAULT,
    updateEmail: (email: String) -> Unit = {  },
    updatePassword: (password: String) -> Unit = {  },
    navigateToWelcome: () -> Unit = {  },
    navigateToRegister: () -> Unit = {  },
    login: () -> Unit = {  }
) {

    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = modifier,
                onClick = { navigateToWelcome() }
            ) {

                Text("Welcome")
            }

            Button(
                modifier = modifier,
                onClick = { navigateToRegister() }
            ) {

                Text("Register")
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                value = state.email,
                onValueChange = { updateEmail(it) }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                value = state.password,
                onValueChange = { updatePassword(it) }
            )

            OutlinedButton(
                modifier = modifier,
                onClick = { login() }
            ) {

                Text("Login")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
