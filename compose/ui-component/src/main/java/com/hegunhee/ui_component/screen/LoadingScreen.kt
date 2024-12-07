package com.hegunhee.ui_component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.ui_component.style.LoadingMessage
import com.hegunhee.ui_component.style.largeTextFontSize

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = LoadingMessage,
            fontSize = largeTextFontSize,
        )
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen(modifier = Modifier)
}
