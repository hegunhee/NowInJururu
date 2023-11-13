package com.hegunhee.nowinjururu.core.navigation.deeplink

sealed interface DeepLink {

    val url : String
    data class Kakao(override val url : String) : DeepLink

    data class Share(val baseUrl : String, val title : String) : DeepLink {
        override val url: String
            get() = "$baseUrl $title"
    }
    data class Twitch(val query : TwitchDeepLinkQuery) : DeepLink {
        override val url: String
            get() = "twitch://open" + query.getDeepLinkQuery()
    }

}





