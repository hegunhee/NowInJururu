package com.hegunhee.nowinjururu.core.navigation.deeplink.type

import android.content.Context

sealed interface DeepLink {

    fun handleDeepLink(context : Context)

}
