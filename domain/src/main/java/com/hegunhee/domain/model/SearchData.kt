package com.hegunhee.domain.model

data class SearchData(
    val streamerLogin: String,
    val streamerId: String,
    val gameName: String,
    val isLive: Boolean,
    val tags: List<String> = emptyList(),
    val profileUrl: String,
    val title: String
)