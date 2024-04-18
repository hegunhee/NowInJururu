package com.hegunhee.compose.app

import com.hegunhee.compose.search.SearchNavGraph
import com.hegunhee.compose.streamer.StreamerNavGraph
import com.hegunhee.maplefinder.searchkakao.SearchKakaoNavGraph

sealed class BottomNavItem(
    val title : Int,val icon : Int, val screenRoute : String
) {
    object Jururu : BottomNavItem(com.hegunhee.ui_component.R.string.jururu, com.hegunhee.ui_component.R.drawable.ic_star_24,SearchKakaoNavGraph.searchKakaoRoute)
    object Streamer : BottomNavItem(com.hegunhee.ui_component.R.string.streamer,com.hegunhee.ui_component.R.drawable.ic_streamer_24,StreamerNavGraph.streamerRoute)
    object Search : BottomNavItem(com.hegunhee.ui_component.R.string.search,com.hegunhee.resource_common.R.drawable.ic_search_24,SearchNavGraph.searchRoute)

}

val bottomNavItems = listOf<BottomNavItem>(BottomNavItem.Jururu,BottomNavItem.Streamer,BottomNavItem.Search)
