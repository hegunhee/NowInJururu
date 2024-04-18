package com.hegunhee.maplefinder.searchkakao

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchKakaoNavGraph(
    paddingValues : PaddingValues,
) {
    composable(route = SearchKakaoNavGraph.searchKakaoRoute) {
        SearchKakaoScreenRoot(paddingValues)
    }
}

object SearchKakaoNavGraph {
    const val searchKakaoRoute = "SearchKakao"
}