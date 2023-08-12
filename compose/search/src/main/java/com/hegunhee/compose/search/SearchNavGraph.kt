package com.hegunhee.compose.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchNavGraph(

) {
    composable(route = SearchNavGraph.searchRoute) {
        SearchScreenRoot()
    }
}

object SearchNavGraph{
    const val searchRoute = "search"
}