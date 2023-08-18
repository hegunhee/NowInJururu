package com.hegunhee.compose.jururu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hegunhee.ui_component.item.OfflineStream
import com.hegunhee.ui_component.item.OnlineStream
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun JururuScreenRoot(
    viewModel : JururuViewModel = hiltViewModel(),
    onNavigateTwitchChannelClick : (String) -> Unit
) {
    JururuScreen(
        uiState = viewModel.uiState.value,
        onNavigateTwitchChannelClick
    )
}

@Composable
fun JururuScreen(
    uiState : JururuUiState,
    onNavigateTwitchChannelClick : (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(LocalPaddingValues.current)) {
        ScreenHeaderText(text = "주르르")
        when(uiState) {
            is JururuUiState.Loading -> {}
            is JururuUiState.Success -> {
                LazyColumn() {
                    items(items = uiState.onlineStreamData, key = {it.streamerId}) { streamData ->
                        OnlineStream(
                            streamerId = streamData.streamerId,
                            streamerName = streamData.streamerName,
                            title = streamData.title,
                            gameName = streamData.gameName,
                            tags = streamData.tags,
                            thumbNailUrl = streamData.thumbnailUrl,
                            profileUrl = streamData.profileUrl,
                            viewerCount = streamData.viewerCount,
                            onTwitchStreamClick = onNavigateTwitchChannelClick,
                            onUnfollowButtonClick = {}
                        )
                    }
                    items(items = uiState.offlineStreamData, key = {it.streamerId}) { streamData ->
                        OfflineStream(
                            streamerId = streamData.streamerId,
                            streamerName = streamData.streamerName,
                            streamerProfileUrl = streamData.profileUrl,
                            onTwitchStreamClick = onNavigateTwitchChannelClick,
                            onUnfollowButtonClick = {}
                        )
                    }
                }
            }
            is JururuUiState.Error -> {}
        }
    }
}

