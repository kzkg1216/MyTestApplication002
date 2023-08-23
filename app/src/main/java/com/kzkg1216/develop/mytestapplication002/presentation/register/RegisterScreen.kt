package com.kzkg1216.develop.mytestapplication002.presentation.register

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
fun RegisterScreenState(
    viewModel: RegisterViewModel,
    navigateToWelcome: () -> Unit,
    navigateToLogin: () -> Unit
) {

    RegisterScreen(
        navigateToWelcome = navigateToWelcome,
        navigateToLogin = navigateToLogin
    )
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToWelcome: () -> Unit = {  },
    navigateToLogin: () -> Unit = {  }
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
            onClick = { navigateToLogin() }
        ) {

            Text("Login")
        }
    }
}