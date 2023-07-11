package com.hegunhee.feature.main.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.net.toUri
import com.hegunhee.feature.main.R

class TwitchAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        appWidgetIds.forEach {
            val pendingIntent = Intent(Intent.ACTION_VIEW).let {
                it.data = "twitch://open?stream=cotton__123".toUri()
                PendingIntent.getActivity(context,0,it,0)
            }

            val views : RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget_twitch
            ).apply {
                setOnClickPendingIntent(R.id.text,pendingIntent)
            }

            appWidgetManager.updateAppWidget(appWidgetIds,views)
        }
    }
}