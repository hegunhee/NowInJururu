package com.hegunhee.compose.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hegunhee.compose.app.ui.theme.NowInJururuTheme
import com.hegunhee.compose.jururu.JururuNavGraph
import com.hegunhee.compose.jururu.jururuNavGraph
import com.hegunhee.compose.search.searchNavGraph
import com.hegunhee.compose.streamer.streamerNavGraph
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowInJururuApp(
    jururuAppScaffoldState : JururuAppScaffoldState = rememberJururuAppScaffoldState()
) {
    NowInJururuTheme() {
        Scaffold(bottomBar = { JururuBottomNavigation(backStackEntry = jururuAppScaffoldState.navController.currentBackStackEntryAsState(), onBottomClick = jururuAppScaffoldState::navigateBottomNavigation)}) { _ ->
            NavHost(navController = jururuAppScaffoldState.navController, startDestination = JururuNavGraph.jururuRoute){
                jururuNavGraph()

                streamerNavGraph()

                searchNavGraph()
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

