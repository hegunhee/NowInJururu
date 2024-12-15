package com.hegunhee.domain.model.platform

sealed interface StreamPlatform {

}

class TwitchStreamer(private val streamerId: String) : StreamPlatform {

}

class TwitchGame(private val gameId: String) : StreamPlatform {

}
