package com.kzkg1216.develop.mytestapplication002.presentation.settings

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

@Composable
fun SettingsScreenState(
    viewModel: SettingsViewModel,
    navigateToWelcome: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        state = state.value as SettingsUiState.Success,
        navigateToWelcome = navigateToWelcome
    )
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsUiState.Success = SettingsUiState.Success.DEFAULT,
    navigateToWelcome: () -> Unit = {  }
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { navigateToWelcome() }
        ) {
            Text(text = "Welcome")
        }

        Text(text = "Settings Screen!")
        Text(text = "Debug mode: ${state.isDebugging}")
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}