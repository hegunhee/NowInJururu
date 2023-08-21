package com.hegunhee.compose.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

val LocalPaddingValues = compositionLocalOf { PaddingValues(0.dp) }
fun NavGraphBuilder.searchNavGraph(
    paddingValues : PaddingValues,
    onNavigateTwitchChannelClick : (String) -> Unit
) {
    composable(route = SearchNavGraph.searchRoute) {
        CompositionLocalProvider(LocalPaddingValues provides paddingValues) {
            SearchScreenRoot(onNavigateTwitchChannelClick = onNavigateTwitchChannelClick)
        }
    }
}

object SearchNavGraph{
    const val searchRoute = "search"
}