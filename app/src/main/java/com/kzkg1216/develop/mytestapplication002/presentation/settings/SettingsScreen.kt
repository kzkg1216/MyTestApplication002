package com.kzkg1216.develop.mytestapplication002.presentation.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingsScreenState(
    viewModel: SettingsViewModel,
    navigateToWelcome: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    SettingsScreen(
        state = state.value as SettingsUiState.Success,
        scrollState = scrollState,
        navigateToWelcome = navigateToWelcome,
        setDebugMode = { viewModel.setDebugMode(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(top = 8.dp),
    state: SettingsUiState.Success = SettingsUiState.Success.DEFAULT,
    scrollState: ScrollState = ScrollState(0),
    navigateToWelcome: () -> Unit = {  },
    setDebugMode: (enable: Boolean) -> Unit = {  }
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
                modifier = buttonModifier,
                onClick = { navigateToWelcome() }
            ) {
                Text(text = "Welcome")
            }

            Column(
                modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                SettingsItem(
                    text = "Debug mode",
                    checkedState = state.isDebugging,
                    updateState = { setDebugMode(it) }
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = "Settings Screen!"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
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