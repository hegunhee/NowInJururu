package com.hegunhee.feature.streamer


sealed class StreamerViewType {

    data class LiveStreamerHeader(val size : Int) : StreamerViewType()

    data class LiveStreamer(
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

    data class UnLiveStreamerHeader(val size : Int) : StreamerViewType()

    data class UnLiveStreamer(val userLogin : String,val userName : String,val profileUrl : String) : StreamerViewType()
}