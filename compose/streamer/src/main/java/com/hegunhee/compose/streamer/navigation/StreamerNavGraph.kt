package com.hegunhee.compose.streamer.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.compose.streamer.StreamerScreenRoot

val LocalPaddingValues = compositionLocalOf { PaddingValues(0.dp) }

const val StreamerRoute = "streamer"

fun NavGraphBuilder.streamerNavGraph(
    paddingValues : PaddingValues,
) {
    composable(route = StreamerRoute) {
        CompositionLocalProvider(LocalPaddingValues provides paddingValues) {
            StreamerScreenRoot()
        }

    }
}
