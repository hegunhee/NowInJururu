package com.hegunhee.nowinjururu.core.navigation.deeplink

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.net.toUri
import com.hegunhee.resource_common.R

internal fun Context.handleTwitchDeepLink(url : String) {
    runCatching {
        isInstalledTwitchAppOrException()
    }.onSuccess {
        openDeepLink(url)
    }.onFailure {
        openPlayStore()
    }
}

private fun Context.openDeepLink(url : String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = url.toUri()
        startActivity(this)
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