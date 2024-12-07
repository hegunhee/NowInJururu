package com.hegunhee.ui_component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.ui_component.style.ErrorMessage
import com.hegunhee.ui_component.style.RetryMessage
import com.hegunhee.ui_component.style.largeTextFontSize
import com.hegunhee.ui_component.style.middleButtonFontSize

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    exception: Throwable,
    onRetryClick: (() -> Unit)?,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = ErrorMessage,
            fontSize = largeTextFontSize,
        )
        if(onRetryClick != null) {
            Button(onRetryClick) {
                Text(
                    RetryMessage,
                    fontSize = middleButtonFontSize,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
        exception = IllegalStateException(),
        onRetryClick = null,
    )
}

@Preview
@Composable
private fun RetryableErrorScreenPreview() {
    ErrorScreen(
        exception = IllegalStateException(),
        onRetryClick = {}
    )
}
