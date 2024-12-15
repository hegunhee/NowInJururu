package com.hegunhee.compose.streamer

import com.hegunhee.domain.model.platform.StreamPlatform
import com.hegunhee.domain.model.platform.TwitchGame
import com.hegunhee.domain.model.twitch.RecommendStreamData
import com.hegunhee.domain.model.twitch.StreamDataType

sealed class StreamItem(val isEmpty: Boolean) {

    data class Online(
        val items: List<StreamDataType.OnlineData>
    ) : StreamItem(items.isEmpty())

    data class Offline(
        val items: List<StreamDataType.OfflineData>
    ) : StreamItem(items.isEmpty())

    data class Recommend(
        val gameId: String,
        val platform: StreamPlatform,
        val items: List<StreamDataType.OnlineData>
    ) : StreamItem(items.isEmpty())
}

fun List<StreamDataType.OnlineData>.toOnlineStreamItem(): StreamItem.Online {
    return StreamItem.Online(items = this)
}

fun List<StreamDataType.OfflineData>.toOfflineStreamItem(): StreamItem.Offline {
    return StreamItem.Offline(items = this)
}

fun RecommendStreamData.toRecommendStreamItem() : StreamItem.Recommend {
    return StreamItem.Recommend(gameId = gameId, platform = platform,items = streams)
}

fun emptyRecommendStreamItem(): StreamItem.Recommend {
    return StreamItem.Recommend(gameId = "", platform = TwitchGame(""),items = emptyList())
}

fun List<StreamDataType>.filterMapOnlineStreamItems(): StreamItem.Online {
    return this.filterIsInstance<StreamDataType.OnlineData>().toOnlineStreamItem()
}

fun List<StreamDataType>.filterMapOfflineStreamItems(): StreamItem.Offline {
    return this.filterIsInstance<StreamDataType.OfflineData>().toOfflineStreamItem()
}

fun List<StreamDataType.OnlineData>.getMostFollowedGameId(): String {
    return this.map { it.gameId }.groupBy { it }.maxBy { it.value.size }.key
}
