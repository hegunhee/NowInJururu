package com.hegunhee.domain.model.twitch

data class StreamerData(
    val streamerId : String,
    val isAlert : Boolean = false
)