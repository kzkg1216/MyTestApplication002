package com.kzkg1216.develop.mytestapplication002.presentation.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    DebugScreen(
        state = state.value as DebugUiState.Success,
        setDebugMode = { viewModel.setDebugMode(it) },
        navigateToWelcome = navigateToWelcome
    )
}

@Composable
fun DebugScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(top = 8.dp)
    ,
    state: DebugUiState.Success = DebugUiState.Success.DEFAULT,
    navigateToWelcome: () -> Unit = {  },
    setDebugMode: (enable: Boolean) -> Unit = {  }
) {

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

        OutlinedButton(
            modifier = modifier,
            onClick = { setDebugMode(true) }
        ) {
            Text(text = "Debug mode ON")
        }

        OutlinedButton(
            modifier = modifier,
            onClick = { setDebugMode(false) }
        ) {
            Text(text = "Debug mode OFF")
        }

        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            text = "Debug Screen!"
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            text = "Debug mode: ${state.isDebugging}"
        )
    }
}

@Preview
@Composable
fun DebugScreenPreview() {
    DebugScreen()
}