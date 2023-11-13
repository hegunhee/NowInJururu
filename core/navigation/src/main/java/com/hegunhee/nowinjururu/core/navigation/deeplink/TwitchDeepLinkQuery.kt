package com.hegunhee.nowinjururu.core.navigation.deeplink

sealed interface TwitchDeepLinkQuery {

    fun getDeepLinkQuery() : String
    object App : TwitchDeepLinkQuery {
        override fun getDeepLinkQuery(): String {
            return ""
        }
    }

    data class Streamer(val streamerId : String) : TwitchDeepLinkQuery {

        override fun getDeepLinkQuery(): String {
            return "?stream=$streamerId"
        }
    }

    data class Game(val gameName : String) : TwitchDeepLinkQuery {

        override fun getDeepLinkQuery(): String {
            return "?game=$gameName"
        }
    }
}