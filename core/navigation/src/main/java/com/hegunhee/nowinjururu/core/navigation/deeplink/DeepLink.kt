package com.hegunhee.nowinjururu.core.navigation.deeplink

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

sealed interface DeepLink {

    fun handleDeepLink(context: Context)

    class Kakao(private val url: String) : DeepLink {

        override fun handleDeepLink(context: Context) {
            Intent(Intent.ACTION_VIEW).apply {
                data = url.toUri()
                context.startActivity(this)
            }
        }

    }

    class Share(
        private val baseUrl: String,
        private val title: String,
    ) : DeepLink {

        override fun handleDeepLink(context: Context) {
            val url = "$baseUrl $title"

            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
                context.startActivity(Intent.createChooser(this, url))
            }
        }

    }

    class TwitchGame(private val gameName: String) : DeepLink {

        override fun handleDeepLink(context: Context) {
            context.handleTwitchDeepLink("twitch://open?game=$gameName")
        }

    }

    class TwitchStreamer(private val streamerId: String) : DeepLink {

        override fun handleDeepLink(context: Context) {
            context.handleTwitchDeepLink("twitch://open?stream=$streamerId")
        }

    }

}
