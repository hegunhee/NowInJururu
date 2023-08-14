package com.hegunhee.compose.jururu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

val LocalPaddingValues = compositionLocalOf { PaddingValues(0.dp) }
fun NavGraphBuilder.jururuNavGraph(
    paddingValues : PaddingValues
) {
    composable(route = JururuNavGraph.jururuRoute) {
        CompositionLocalProvider(LocalPaddingValues provides paddingValues) {
            JururuScreenRoot()
        }
    }
}

object JururuNavGraph {
    const val jururuRoute = "Jururu"
}