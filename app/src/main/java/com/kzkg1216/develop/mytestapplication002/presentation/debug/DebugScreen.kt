package com.kzkg1216.develop.mytestapplication002.presentation.debug

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

@Composable
fun DebugScreenState(
    viewModel: DebugViewModel,
    navigateToWelcome: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    DebugScreen(
        state = state.value as DebugUiState.Success,
        setDebugMode = { viewModel.setDebugMode(it) },
        navigateToWelcome = navigateToWelcome
    )
}

@Composable
fun DebugScreen(
    modifier: Modifier = Modifier,
    state: DebugUiState.Success = DebugUiState.Success.DEFAULT,
    navigateToWelcome: () -> Unit = {  },
    setDebugMode: (enable: Boolean) -> Unit = {  }
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

        Text(text = "Debug Screen!")
        Text(text = "Debug mode: ${state.isDebugging}")

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { setDebugMode(true) }
        ) {
            Text(text = "Debug mode ON")
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { setDebugMode(false) }
        ) {
            Text(text = "Debug mode OFF")
        }
    }
}

@Preview
@Composable
fun DebugScreenPreview() {
    DebugScreen()
}