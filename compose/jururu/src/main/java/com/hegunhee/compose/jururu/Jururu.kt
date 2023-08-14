package com.hegunhee.compose.jururu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun JururuScreenRoot(
    viewModel : JururuViewModel = hiltViewModel()
) {
    JururuScreen(uiState = viewModel.uiState.value)
}

@Composable
fun JururuScreen(uiState : JururuUiState) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(LocalPaddingValues.current)) {
        ScreenHeaderText(text = "주르르")
        when(uiState) {
            is JururuUiState.Loading -> {
                Text(text = uiState.toString())
            }
            is JururuUiState.Success -> {
                Text(text = uiState.streamerList.toString())
            }
            is JururuUiState.Error -> {
                Text(text = uiState.toString())
            }
        }
    }
}

