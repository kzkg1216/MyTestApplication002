package com.kzkg1216.develop.mytestapplication002.presentation.settings

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(top = 8.dp),
    state: SettingsUiState.Success = SettingsUiState.Success.DEFAULT,
    navigateToWelcome: () -> Unit = {  }
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
                Text(text = "Welcome")
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                text = "Settings Screen!"
            )

            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                text = "Debug mode: ${state.isDebugging}"
            )
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}