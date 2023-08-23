package com.hegunhee.compose.streamer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

val LocalPaddingValues = compositionLocalOf { PaddingValues(0.dp) }

fun NavGraphBuilder.streamerNavGraph(
    paddingValues : PaddingValues,
    onNavigateTwitchChannelClick : (String) -> Unit
) {
    composable(route = StreamerNavGraph.streamerRoute) {
        CompositionLocalProvider(LocalPaddingValues provides paddingValues) {
            StreamerScreenRoot(onNavigateTwitchChannelClick = onNavigateTwitchChannelClick)
        }

    }
}

object StreamerNavGraph{
    const val streamerRoute = "streamer"
}