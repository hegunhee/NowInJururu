package com.hegunhee.feature.common.twitch

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import com.hegunhee.resource_common.R

fun Context.handleTwitchDeepLink(deepLink : TwitchDeepLink) {
    runCatching {
        isInstalledTwitchAppOrException()
    }.onSuccess {
        openDeepLink(deepLink)
    }.onFailure {
        openPlayStore()
    }
}

private fun Context.isInstalledTwitchAppOrException() : PackageInfo {
    return packageManager.getPackageInfo(getString(R.string.twitchPackageName), PackageManager.PackageInfoFlags.of(0L))
}

private fun Context.openDeepLink(deepLink: TwitchDeepLink) {
    val uri = getDeepLinkUri(this,deepLink.getDeepLinkQuery())
    Intent(Intent.ACTION_VIEW).apply {
        data = uri
        startActivity(this)
    }
}

private fun Context.openPlayStore() {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(getString(R.string.playStoreTwitchDeepLink))
        startActivity(this)
    }
}

private fun getDeepLinkUri(context : Context,query : String) : Uri {
    return Uri.parse(context.getString(R.string.twitchAppBaseUrl)+query)
}