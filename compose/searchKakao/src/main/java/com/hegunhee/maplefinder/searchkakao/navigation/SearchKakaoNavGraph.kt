package com.hegunhee.maplefinder.searchkakao.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.maplefinder.searchkakao.SearchKakaoScreenRoot

const val SEARCH_KAKAO_ROUTE = "search-kakao-route"

fun NavGraphBuilder.searchKakaoNavGraph(
    paddingValues : PaddingValues,
) {
    composable(route = SEARCH_KAKAO_ROUTE) {
        SearchKakaoScreenRoot(paddingValues)
    }
}
