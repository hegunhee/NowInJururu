package com.hegunhee.compose.streamer

import com.hegunhee.domain.model.twitch.StreamDataType

sealed class StreamItem(val isItemsNotEmpty: Boolean) {

    data class Online(
        val items: List<StreamDataType.OnlineData>
    ) : StreamItem(items.isNotEmpty())

    data class Offline(
        val items: List<StreamDataType.OfflineData>
    ) : StreamItem(items.isNotEmpty())

    data class Recommend(
        val gameName: String,
        val items: List<StreamDataType.OnlineData>
    ) : StreamItem(items.isNotEmpty())
}

fun List<StreamDataType.OnlineData>.toOnlineStreamItem(): StreamItem.Online {
    return StreamItem.Online(items = this)
}

fun List<StreamDataType.OfflineData>.toOfflineStreamItem(): StreamItem.Offline {
    return StreamItem.Offline(items = this)
}

fun List<StreamDataType.OnlineData>.toRecommendStreamItem(gameName: String): StreamItem.Recommend {
    return StreamItem.Recommend(gameName = gameName, items = this)
}

fun emptyRecommendStreamItem(): StreamItem.Recommend {
    return StreamItem.Recommend(gameName = "", items = emptyList())
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
