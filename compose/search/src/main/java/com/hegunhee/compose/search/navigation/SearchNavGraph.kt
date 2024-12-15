package com.hegunhee.compose.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.compose.search.SearchScreenRoot

const val SearchTwitch = "search-twitch-route"

val LocalPaddingValues = compositionLocalOf { PaddingValues(0.dp) }

fun NavGraphBuilder.searchNavGraph(
    paddingValues : PaddingValues,
) {
    composable(route = SearchTwitch) {
        CompositionLocalProvider(LocalPaddingValues provides paddingValues) {
            SearchScreenRoot()
        }
    }
}
