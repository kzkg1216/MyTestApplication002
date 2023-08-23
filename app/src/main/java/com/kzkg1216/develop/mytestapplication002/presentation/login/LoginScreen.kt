package com.kzkg1216.develop.mytestapplication002.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreenState(
    viewModel: LoginViewModel,
    navigateToWelcome: () -> Unit,
    navigateToRegister: () -> Unit
) {

    LoginScreen(
        navigateToWelcome = navigateToWelcome,
        navigateToRegister = navigateToRegister
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToWelcome: () -> Unit = {  },
    navigateToRegister: () -> Unit = {  }
) {

    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { navigateToWelcome() }
        ) {

            Text("Welcome")
        }

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { navigateToRegister() }
        ) {

            Text("Register")
        }
    }
}