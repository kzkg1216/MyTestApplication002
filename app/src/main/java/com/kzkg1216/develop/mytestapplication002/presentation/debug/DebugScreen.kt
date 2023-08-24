package com.kzkg1216.develop.mytestapplication002.presentation.debug

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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DebugScreenState(
    viewModel: DebugViewModel,
    navigateToWelcome: () -> Unit
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    DebugScreen(
        state = state.value as DebugUiState.Success,
        scrollState = scrollState,
        navigateToWelcome = navigateToWelcome,
        setDebugMode = { viewModel.setDebugMode(it) },
        setRegisterStatus = { viewModel.setRegisterStatus(it) },
        setLoginStatus = { viewModel.setLoginStatus(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebugScreen(
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = modifier.fillMaxWidth().height(56.dp).padding(top = 8.dp),
    state: DebugUiState.Success = DebugUiState.Success.DEFAULT,
    scrollState: ScrollState = ScrollState(0),
    navigateToWelcome: () -> Unit = {  },
    setDebugMode: (enable: Boolean) -> Unit = {  },
    setRegisterStatus: (status: Boolean) -> Unit = {  },
    setLoginStatus: (status: Boolean) -> Unit = {  }
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

            OutlinedButton(
                modifier = buttonModifier,
                onClick = { setDebugMode(true) }
            ) {
                Text(text = "Debug mode ON")
            }

            OutlinedButton(
                modifier = buttonModifier,
                onClick = { setDebugMode(false) }
            ) {
                Text(text = "Debug mode OFF")
            }

            OutlinedButton(
                modifier = buttonModifier,
                onClick = { setRegisterStatus(true) }
            ) {
                Text(text = "Register status ON")
            }

            OutlinedButton(
                modifier = buttonModifier,
                onClick = { setRegisterStatus(false) }
            ) {
                Text(text = "Register status OFF")
            }

            OutlinedButton(
                modifier = buttonModifier,
                onClick = { setLoginStatus(true) }
            ) {
                Text(text = "Login status ON")
            }

            OutlinedButton(
                modifier = buttonModifier,
                onClick = { setLoginStatus(false) }
            ) {
                Text(text = "Login status OFF")
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = "Debug Screen!"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = "Debug mode: ${state.isDebugging}"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = "Register status: ${state.isRegistered}"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = "Login status: ${state.isLoggedIn}"
            )
        }
    }
}

@Preview
@Composable
fun DebugScreenPreview() {
    DebugScreen()
}