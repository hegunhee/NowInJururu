package com.hegunhee.compose.jururu

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.jururuNavGraph(

) {
    composable(route = JururuNavGraph.jururuRoute) {
        JururuScreenRoot()
    }
}

object JururuNavGraph {
    const val jururuRoute = "Jururu"
}