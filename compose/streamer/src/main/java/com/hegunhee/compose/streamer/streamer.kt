package com.hegunhee.compose.streamer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun StreamerScreenRoot(
    onNavigateTwitchChannelClick : (String) -> Unit,
) {
    StreamerScreen(
        onNavigateTwitchChannelClick = onNavigateTwitchChannelClick
    )
}

@Composable
fun StreamerScreen(
    onNavigateTwitchChannelClick: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalPaddingValues.current)
    ) {
        ScreenHeaderText(text = "스트리머")
    }
}