package com.hegunhee.compose.app

import com.hegunhee.compose.jururu.JururuNavGraph
import com.hegunhee.compose.search.SearchNavGraph
import com.hegunhee.compose.streamer.StreamerNavGraph

sealed class BottomNavItem(
    val title : Int,val icon : Int, val screenRoute : String
) {
    object Jururu : BottomNavItem(com.hegunhee.ui_component.R.string.jururu, com.hegunhee.ui_component.R.drawable.ic_star_24,JururuNavGraph.jururuRoute)
    object Streamer : BottomNavItem(com.hegunhee.ui_component.R.string.streamer,com.hegunhee.ui_component.R.drawable.ic_streamer_24,StreamerNavGraph.streamerRoute)
    object Search : BottomNavItem(com.hegunhee.ui_component.R.string.search,com.hegunhee.ui_component.R.drawable.ic_search_24,SearchNavGraph.searchRoute)

}

val bottomNavItems = listOf<BottomNavItem>(BottomNavItem.Jururu,BottomNavItem.Streamer,BottomNavItem.Search)