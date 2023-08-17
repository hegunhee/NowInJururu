package com.hegunhee.compose.app.twitchUtil

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.net.toUri
import com.hegunhee.resource_common.R

fun navigateTwitchChannelDeepLink(context : Context, channelId : String) {
    context.run {
        runCatching {
            isInstalledTwitchAppOrException()
        }.onSuccess {
            navigateChannelDeepLink(channelId)
        }.onFailure {
            openPlayStore()
        }
    }
}

private fun Context.isInstalledTwitchAppOrException() : PackageInfo {
    return packageManager.getPackageInfo(getString(R.string.twitchPackageName), PackageManager.PackageInfoFlags.of(0L))
}

private fun Context.navigateChannelDeepLink(channelId : String) {
    val uri = (getString(R.string.twitchAppBaseUrl)+"?stream=$channelId").toUri()
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