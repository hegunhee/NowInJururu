package com.hegunhee.domain.model.twitch

/**
 * EmptyData에는 Streamer Info 정보들이 담길예정
 */

sealed class StreamDataType() {
    data class OnlineData(
        val gameId: String,
        val gameName: String,
        val id: String,
        val isMature: Boolean,
        val language: String,
        val startedAt: String,
        val tags: List<String> = emptyList(),
        val thumbnailUrl: String,
        val profileUrl : String,
        val title: String,
        val type: String,
        val userId: String,
        val streamerId: String,
        val streamerName: String,
        val viewerCount: String
    ) : StreamDataType()

    data class OfflineData(val streamerId : String, val streamerName : String, val profileUrl : String) : StreamDataType()

    companion object {
        val RecommendStreamThumbNailWidth = 250
        val RecommendStreamThumbNailHeight = 200

    }
}
