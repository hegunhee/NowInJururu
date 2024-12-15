package com.hegunhee.nowinjururu.core.navigation.deeplink.type

import android.content.Context
import android.content.Intent

class Share(
    private val baseUrl: String,
    private val title: String,
) : DeepLink{

    override fun handleDeepLink(context: Context) {
        val url = "$baseUrl $title"

        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,url)
            context.startActivity(Intent.createChooser(this,url))
        }
    }

}
