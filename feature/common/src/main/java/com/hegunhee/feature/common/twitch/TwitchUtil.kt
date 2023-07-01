package com.hegunhee.feature.common.twitch

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import com.hegunhee.feature.common.R

fun Context.handleOpenTwitchApp(streamerLogin : String) {
    runCatching {
        isInstalledTwitchAppOrException()
    }.onSuccess {
        openStreamerStream(streamerLogin)
    }.onFailure {
        openPlayStore()
    }
}

private fun Context.isInstalledTwitchAppOrException() : PackageInfo {
    return packageManager.getPackageInfo(getString(R.string.twitchPackageName), PackageManager.PackageInfoFlags.of(0L))
}

private fun Context.openStreamerStream(streamerLogin : String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = streamerLogin.toStreamerUri(this@openStreamerStream)
        startActivity(this)
    }
}

private fun Context.openPlayStore() {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(getString(R.string.playStoreTwitchDeepLink))
        startActivity(this)
    }
}

fun String.toStreamerUri(context : Context) : Uri {
    return Uri.parse("${context.getString(R.string.twitchStreamDeepLink)}$this")
}