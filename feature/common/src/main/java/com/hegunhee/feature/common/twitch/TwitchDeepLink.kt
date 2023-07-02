package com.hegunhee.feature.common.twitch

sealed class TwitchDeepLink {

    object TwitchApp : TwitchDeepLink()

    data class Streamer(val streamerId : String) : TwitchDeepLink()

    data class Game(val gameName : String) : TwitchDeepLink()
}