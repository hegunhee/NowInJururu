package com.hegunhee.compose.streamer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hegunhee.ui_component.item.OfflineStream
import com.hegunhee.ui_component.item.OnlineStream
import com.hegunhee.ui_component.item.RecommendStream
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun StreamerScreenRoot(
    onNavigateTwitchChannelClick : (String) -> Unit,
    viewModel : StreamerViewModel = hiltViewModel()
) {
    StreamerScreen(
        uiModel = viewModel.uiModel.value,
        onNavigateTwitchChannelClick = onNavigateTwitchChannelClick
    )
}

@Composable
fun StreamerScreen(
    uiModel : StreamerUiModel,
    onNavigateTwitchChannelClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalPaddingValues.current)
            .padding(horizontal = 20.dp)
    ) {
        ScreenHeaderText(text = "스트리머")
        when(uiModel) {
            StreamerUiModel.Loading -> {}
            is StreamerUiModel.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiModel.streamItem.forEach {
                        streamerItem(it,onNavigateTwitchChannelClick)
                    }
                }
            }
            StreamerUiModel.Error -> {}

        }
        uiModel.toString()
    }
}

fun LazyListScope.streamerItem(
    streamItem : StreamItem,
    onNavigateTwitchChannelClick: (String) -> Unit,
) {
    if(streamItem.isItemsNotEmpty) {
        when(streamItem) {
            is StreamItem.Online -> {
                item {
                    Text(text = "온라인",fontSize = 25.sp)
                }
                items(items = streamItem.items, key = {it.streamerId}) {
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
                        onMoreButtonClick = {}
                    )
                }

            }
            is StreamItem.Offline -> {
                item {
                    Text(text = "오프라인",fontSize = 25.sp)
                }
                items(items = streamItem.items, key = {it.streamerId}) {
                    OfflineStream(
                        streamerId = it.streamerId,
                        streamerName = it.streamerName,
                        streamerProfileUrl = it.profileUrl,
                        onTwitchStreamClick = onNavigateTwitchChannelClick,
                        onMoreButtonClick = {}
                    )
                }
            }
            is StreamItem.Recommend -> {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(text = "추천 생방송 채널",fontSize = 20.sp)
                        Text(text = "트위치 앱에서 보기",fontSize = 15.sp, color = colorResource(id = com.hegunhee.resource_common.R.color.violet),modifier = Modifier.clickable {
                            onNavigateTwitchChannelClick("twitch://open?game=${streamItem.gameName}")
                        })
                    }
                }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        items(items = streamItem.items, key = { "recommend" + it.streamerId}) {
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
}
