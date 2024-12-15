package com.hegunhee.domain.model.platform

sealed interface StreamPlatform {

}

class TwitchStreamer(val streamerId: String) : StreamPlatform {

}

class TwitchGame(val gameName: String) : StreamPlatform {

}
