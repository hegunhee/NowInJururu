package com.hegunhee.compose.streamer

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.streamerNavGraph(

) {
    composable(route = StreamerNavGraph.streamerRoute) {
        StreamerScreenRoot()
    }
}

object StreamerNavGraph{
    const val streamerRoute = "streamer"
}