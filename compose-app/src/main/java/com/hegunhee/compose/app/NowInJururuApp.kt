package com.hegunhee.compose.app

import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hegunhee.compose.app.ui.theme.NowInJururuTheme
import com.hegunhee.compose.search.navigation.searchNavGraph
import com.hegunhee.compose.streamer.navigation.streamerNavGraph
import com.hegunhee.maplefinder.searchkakao.navigation.SEARCH_KAKAO_ROUTE
import com.hegunhee.maplefinder.searchkakao.navigation.searchKakaoNavGraph
import kotlinx.coroutines.CoroutineScope

@Composable
fun NowInJururuApp(
    jururuAppScaffoldState : JururuAppScaffoldState = rememberJururuAppScaffoldState(),
    twitchNavigationController : TwitchNavigationController = rememberTwitchNavigationController()
) {
    NowInJururuTheme() {
        Scaffold(bottomBar = { JururuBottomNavigation(backStackEntry = jururuAppScaffoldState.navController.currentBackStackEntryAsState(), onBottomClick = jururuAppScaffoldState::navigateBottomNavigation)}) { paddingValues ->
            NavHost(navController = jururuAppScaffoldState.navController, startDestination = SEARCH_KAKAO_ROUTE){
                searchKakaoNavGraph(
                    paddingValues =  paddingValues
                )

                streamerNavGraph(
                    paddingValues = paddingValues,
                )

                searchNavGraph(
                    paddingValues =  paddingValues,
                )
            }
        }
    }
}

@Composable
fun rememberJururuAppScaffoldState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
) : JururuAppScaffoldState {
    return remember(key1 = coroutineScope, key2 = navController) {
        JururuAppScaffoldState(
            coroutineScope,
            navController
        )
    }
}

class JururuAppScaffoldState(
    val coroutineScope : CoroutineScope,
    val navController: NavHostController,
) {
    fun navigateBottomNavigation(index : Int) {
        navController.navigate(bottomNavItems[index].screenRoute) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberTwitchNavigationController() : TwitchNavigationController {
    val context = LocalContext.current
    return remember(context) {
        TwitchNavigationController(context)
    }
}

class TwitchNavigationController(
    private val context : Context
) {

}