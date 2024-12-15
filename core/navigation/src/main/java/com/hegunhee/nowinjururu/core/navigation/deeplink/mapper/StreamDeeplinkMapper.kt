package com.hegunhee.nowinjururu.core.navigation.deeplink.mapper

import com.hegunhee.domain.model.platform.StreamPlatform
import com.hegunhee.domain.model.platform.TwitchGame
import com.hegunhee.domain.model.platform.TwitchStreamer
import com.hegunhee.nowinjururu.core.navigation.deeplink.type.DeepLink

fun StreamPlatform.toDeepLink(): DeepLink {
    return when (this) {
        is TwitchStreamer -> {
            toDeepLink()
        }

        is TwitchGame -> {
            toDeepLink()
        }
    }
}

private fun TwitchStreamer.toDeepLink(): DeepLink {
    return DeepLink.TwitchStreamer(streamerId)
}

private fun TwitchGame.toDeepLink(): DeepLink {
    return DeepLink.TwitchGame(gameName)
}