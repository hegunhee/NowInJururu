package com.hegunhee.feature.common.twitch

sealed interface TwitchDeepLink {

    fun getDeepLinkQuery() : String

    object TwitchApp : TwitchDeepLink {

        override fun getDeepLinkQuery(): String {
            return ""
        }
    }

    data class Streamer(val streamerId : String) : TwitchDeepLink {

        override fun getDeepLinkQuery(): String {
            return "?stream=$streamerId"
        }
    }

    data class Game(val gameName : String) : TwitchDeepLink {

        override fun getDeepLinkQuery(): String {
            return "?game=$gameName"
        }
    }
}