package com.hegunhee.compose.streamer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.compose.streamer.navigation.LocalPaddingValues
import com.hegunhee.ui_component.item.OfflineStream
import com.hegunhee.ui_component.item.OnlineStream
import com.hegunhee.ui_component.item.RecommendStream
import com.hegunhee.ui_component.text.ScreenHeaderText
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.hegunhee.ui_component.R
import com.hegunhee.ui_component.screen.ErrorScreen
import com.hegunhee.ui_component.screen.LoadingScreen
import com.hegunhee.ui_component.style.BottomSheetTitle.StreamerTitle
import com.hegunhee.ui_component.style.OfflineText
import com.hegunhee.ui_component.style.OnlineText
import com.hegunhee.ui_component.style.RecommendOnlineMessage
import com.hegunhee.ui_component.style.RequestText
import com.hegunhee.ui_component.style.TwitchShowMessage
import com.hegunhee.ui_component.style.followCancelMessage
import com.hegunhee.ui_component.style.largeTextFontSize
import com.hegunhee.ui_component.style.middleTextFontSize

@Composable
fun StreamerScreenRoot(
    onNavigateTwitchChannelClick : (String) -> Unit,
    viewModel : StreamerViewModel = hiltViewModel(),
) {
    StreamerScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        onNavigateTwitchChannelClick = onNavigateTwitchChannelClick,
        onUnfollowStreamerClick = viewModel::onUnfollowStreamerClick,
        request = viewModel::request
    )
}

@Composable
fun StreamerScreen(
    uiState : StreamerUiState,
    onNavigateTwitchChannelClick: (String) -> Unit,
    onUnfollowStreamerClick : (String) -> Unit,
    request : () -> Unit,
) {
    var dialogShow by remember { mutableStateOf(Pair(false, "")) }
    val showDialog: (String) -> Unit = { streamerId -> dialogShow = Pair(true, streamerId) }
    val dismissDialog = { dialogShow = Pair(false, "") }

    if (dialogShow.first) {
        StreamerBottomSheet(
            streamerId = dialogShow.second,
            dismissDialog = dismissDialog,
            onUnfollowStreamerClick = onUnfollowStreamerClick
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalPaddingValues.current)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ScreenHeaderText(text = StreamerTitle)
            Image(
                painter = painterResource(id = R.drawable.ic_request_24),
                contentDescription = RequestText,
                modifier = Modifier
                    .clickable { request() }
                    .size(50.dp)
                    .padding(top = 10.dp))
        }

        when (uiState) {
            StreamerUiState.Loading -> {
                LoadingScreen()
            }
            is StreamerUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(com.hegunhee.resource_common.R.dimen.item_between_middle))
                ) {
                    uiState.streamItem.forEach {
                        streamerItem(it, onNavigateTwitchChannelClick, showDialog)
                    }
                }
            }

            is StreamerUiState.Error -> {
                ErrorScreen(exception = uiState.exception, onRetryClick = request)
            }

        }
    }
}

@Composable
fun StreamerBottomSheet(
    streamerId : String,
    dismissDialog : () -> Unit,
    onUnfollowStreamerClick : (String) -> Unit,
) {
    BottomSheetDialog(onDismissRequest = dismissDialog) {
        Surface(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = followCancelMessage(streamerId), modifier = Modifier.clickable {
                    onUnfollowStreamerClick(streamerId)
                    dismissDialog()
                })
            }
        }
    }
}

fun LazyListScope.streamerItem(
    streamItem: StreamItem,
    onNavigateTwitchChannelClick: (String) -> Unit,
    onMoreButtonClick: (String) -> Unit,
) {
    if (streamItem.isEmpty) {
        return
    }
    when (streamItem) {
        is StreamItem.Online -> {
            item {
                Text(
                    text = OnlineText,
                    fontSize = largeTextFontSize,
                    modifier = Modifier.padding(horizontal = dimensionResource(com.hegunhee.resource_common.R.dimen.header_start_padding))
                )
            }
            items(items = streamItem.items, key = { it.streamerId }) {
                OnlineStream(
                    streamerId = it.streamerId,
                    streamerName = it.streamerName,
                    title = it.title,
                    gameName = it.gameName,
                    tags = it.tags,
                    thumbNailUrl = it.thumbnailUrl,
                    profileUrl = it.profileUrl,
                    viewerCount = it.viewerCount,
                    onTwitchStreamClick = onNavigateTwitchChannelClick,
                    onMoreButtonClick = onMoreButtonClick
                )
            }
        }

        is StreamItem.Offline -> {
            item {
                Text(
                    text = OfflineText,
                    fontSize = largeTextFontSize,
                    modifier = Modifier.padding(horizontal = dimensionResource(com.hegunhee.resource_common.R.dimen.header_start_padding))
                )
            }
            items(items = streamItem.items, key = { it.streamerId }) {
                OfflineStream(
                    streamerId = it.streamerId,
                    streamerName = it.streamerName,
                    streamerProfileUrl = it.profileUrl,
                    onTwitchStreamClick = onNavigateTwitchChannelClick,
                    onMoreButtonClick = onMoreButtonClick
                )
            }
        }

        is StreamItem.Recommend -> {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(com.hegunhee.resource_common.R.dimen.header_start_padding)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(com.hegunhee.resource_common.R.dimen.item_between_middle)),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = RecommendOnlineMessage, fontSize = largeTextFontSize)
                    Text(
                        text = TwitchShowMessage,
                        fontSize = middleTextFontSize,
                        color = colorResource(id = com.hegunhee.resource_common.R.color.violet),
                        modifier = Modifier.clickable {
                            onNavigateTwitchChannelClick("twitch://open?game=${streamItem.gameName}")
                        })
                }
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(dimensionResource(com.hegunhee.resource_common.R.dimen.item_between_large))) {
                    items(items = streamItem.items, key = { "recommend" + it.streamerId }) {
                        RecommendStream(
                            streamerId = it.streamerId,
                            streamerName = it.streamerName,
                            title = it.title,
                            gameName = it.gameName,
                            tags = it.tags,
                            thumbNailUrl = it.thumbnailUrl,
                            profileUrl = it.profileUrl,
                            viewerCount = it.viewerCount,
                            onTwitchStreamClick = onNavigateTwitchChannelClick
                        )
                    }
                }
            }
        }
    }
}
