package com.hegunhee.nowinjururu.core.navigation.deeplink.type

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class Kakao(private val url : String) : DeepLink {

    override fun handleDeepLink(context: Context) {
        Intent(Intent.ACTION_VIEW).apply {
            data = url.toUri()
            context.startActivity(this)
        }
    }

}
