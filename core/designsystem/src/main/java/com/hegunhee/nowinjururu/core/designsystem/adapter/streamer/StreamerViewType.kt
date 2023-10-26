package com.hegunhee.nowinjururu.core.designsystem.adapter.streamer


sealed class StreamerViewType {

    data class OnlineStreamerHeader(val size : Int) : StreamerViewType()

    data class OnlineStreamer(
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
        val userLogin: String,
        val userName: String,
        val viewerCount: String
    ) : StreamerViewType()

    data class OfflineStreamerHeader(val size : Int) : StreamerViewType()

    data class OfflineStreamer(val userLogin : String, val userName : String, val profileUrl : String) : StreamerViewType()
}