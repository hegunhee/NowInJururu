package com.hegunhee.nowinjururu.core.navigation.deeplink

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.net.toUri
import com.hegunhee.resource_common.R

fun Context.handleDeepLink(deepLink : DeepLink) {
    when(deepLink) {
        is DeepLink.Kakao -> {
            openDeepLink(deepLink.url)
        }
        is DeepLink.Share -> {
            shareUrl(deepLink.url)
        }
        is DeepLink.Twitch ->{
            handleTwitchDeepLink(deepLink.url)
        }
    }
}

private fun Context.openDeepLink(url : String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = url.toUri()
        startActivity(this)
    }
}

private fun Context.shareUrl(url : String) {
    Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT,url)
        startActivity(Intent.createChooser(this,url))
    }
}

private fun Context.handleTwitchDeepLink(url : String) {
    runCatching {
        isInstalledTwitchAppOrException()
    }.onSuccess {
        openDeepLink(url)
    }.onFailure {
        openPlayStore()
    }
}

private fun Context.isInstalledTwitchAppOrException() : PackageInfo {
    return packageManager.getPackageInfo(getString(R.string.twitchPackageName), PackageManager.PackageInfoFlags.of(0L))
}

private fun Context.openPlayStore() {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(getString(R.string.playStoreTwitchDeepLink))
        startActivity(this)
    }
}