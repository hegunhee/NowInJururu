package com.hegunhee.compose.app

import com.hegunhee.compose.search.SearchNavGraph
import com.hegunhee.compose.streamer.navigation.StreamerRoute
import com.hegunhee.maplefinder.searchkakao.navigation.SEARCH_KAKAO_ROUTE
import com.hegunhee.ui_component.style.BottomSheetTitle.KakaoSearchTitle
import com.hegunhee.ui_component.style.BottomSheetTitle.StreamerTitle
import com.hegunhee.ui_component.style.BottomSheetTitle.TwitchSearchTitle

sealed class BottomNavItem(
    val title : String,val icon : Int, val screenRoute : String
) {
    object KakaoSearch : BottomNavItem(KakaoSearchTitle, com.hegunhee.ui_component.R.drawable.ic_star_24, SEARCH_KAKAO_ROUTE)
    object Streamer : BottomNavItem(StreamerTitle,com.hegunhee.ui_component.R.drawable.ic_streamer_24, StreamerRoute)
    object Search : BottomNavItem(TwitchSearchTitle,com.hegunhee.resource_common.R.drawable.ic_search_24,SearchNavGraph.searchRoute)

}

val bottomNavItems = listOf<BottomNavItem>(BottomNavItem.KakaoSearch,BottomNavItem.Streamer,BottomNavItem.Search)
