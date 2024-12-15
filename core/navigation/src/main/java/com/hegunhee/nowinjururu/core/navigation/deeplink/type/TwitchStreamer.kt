package com.hegunhee.nowinjururu.core.navigation.deeplink.type

import android.content.Context
import com.hegunhee.nowinjururu.core.navigation.deeplink.handleTwitchDeepLink

class TwitchStreamer(private val streamerId: String) : DeepLink {

    override fun handleDeepLink(context: Context) {
        context.handleTwitchDeepLink("twitch://open?stream=$streamerId")
    }

}
