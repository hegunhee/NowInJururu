package com.hegunhee.domain.model.twitch

import com.hegunhee.domain.model.ImageUrl
import com.hegunhee.domain.model.platform.StreamPlatform
import com.hegunhee.domain.model.platform.TwitchStreamer

data class SearchData(
    val platform: StreamPlatform,
    val streamerId: String,
    val streamerName: String,
    val gameName: String,
    val isLive: Boolean,
    val tags: List<String> = emptyList(),
    val profileUrl: ImageUrl,
    val title: String
) {

    fun isEmpty() :Boolean{
        return this == EMPTY
    }
    companion object {
        val EMPTY = SearchData(TwitchStreamer(""),"","","",false, emptyList(),"","")
    }
}