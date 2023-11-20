package com.hegunhee.domain.model.twitch

import com.hegunhee.domain.model.ImageUrl

data class SearchData(
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
        val EMPTY = SearchData("","","",false, emptyList(),"","")
    }
}