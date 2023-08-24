package com.kzkg1216.develop.mytestapplication002.presentation.register

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun RegisterScreenState(
    viewModel: RegisterViewModel,
    navigateToWelcome: () -> Unit,
    navigateToLogin: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    RegisterScreen(
        state = state.value as RegisterUiState.Success,
        scrollState = scrollState,
        updateEmail = { viewModel.updateEmail(it) },
        updatePassword = { viewModel.updatePassword(it) },
        navigateToWelcome = navigateToWelcome,
        navigateToLogin = navigateToLogin
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(top = 8.dp),
    state: RegisterUiState.Success = RegisterUiState.Success.DEFAULT,
    scrollState: ScrollState = ScrollState(0),
    updateEmail: (email: String) -> Unit = {  },
    updatePassword: (password: String) -> Unit = {  },
    navigateToWelcome: () -> Unit = {  },
    navigateToLogin: () -> Unit = {  },
    register: () -> Unit = {  }
) {

    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(scrollState),
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
                onClick = { navigateToLogin() }
            ) {

                Text("Login")
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
                onClick = { register() }
            ) {

                Text("Register")
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}