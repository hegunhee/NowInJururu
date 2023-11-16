package com.hegunhee.domain.model.twitch

data class SearchData(
    val streamerId: String,
    val streamerName: String,
    val gameName: String,
    val isLive: Boolean,
    val tags: List<String> = emptyList(),
    val profileUrl: String,
    val title: String
) {
    companion object {
        val EMPTY = SearchData("","","",false, emptyList(),"","")
    }
}