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
        uiState = viewModel.uiModel.value,
        onNavigateTwitchChannelClick,
        onUnfollowButtonClick = viewModel::onUnfollowButtonClick
    )
}

@Composable
fun JururuScreen(
    uiState : JururuUiModel,
    onNavigateTwitchChannelClick : (String) -> Unit,
    onUnfollowButtonClick : (String) -> Unit
) {
    val onMoreButtonClick : (String) -> Unit = { }
    Column(modifier = Modifier.fillMaxSize().padding(LocalPaddingValues.current)) {
        ScreenHeaderText(text = "주르르")
        when(uiState) {
            is JururuUiModel.Loading -> {}
            is JururuUiModel.Success -> {
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
                            onMoreButtonClick = onMoreButtonClick
                        )
                    }
                    items(items = uiState.offlineStreamData, key = {it.streamerId}) { streamData ->
                        OfflineStream(
                            streamerId = streamData.streamerId,
                            streamerName = streamData.streamerName,
                            streamerProfileUrl = streamData.profileUrl,
                            onTwitchStreamClick = onNavigateTwitchChannelClick,
                            onMoreButtonClick = onMoreButtonClick
                        )
                    }
                }
            }
            is JururuUiModel.Error -> {}
        }
    }
}

