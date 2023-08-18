package com.hegunhee.compose.app

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.hegunhee.compose.jururu.JururuNavGraph
import com.hegunhee.compose.jururu.jururuNavGraph
import com.hegunhee.compose.search.searchNavGraph
import com.hegunhee.compose.streamer.streamerNavGraph
import com.hegunhee.resource_common.R
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowInJururuApp(
    jururuAppScaffoldState : JururuAppScaffoldState = rememberJururuAppScaffoldState(),
    twitchNavigationController : TwitchNavigationController = rememberTwitchNavigationController()
) {
    NowInJururuTheme() {
        Scaffold(bottomBar = { JururuBottomNavigation(backStackEntry = jururuAppScaffoldState.navController.currentBackStackEntryAsState(), onBottomClick = jururuAppScaffoldState::navigateBottomNavigation)}) { paddingValues ->
            NavHost(navController = jururuAppScaffoldState.navController, startDestination = JururuNavGraph.jururuRoute){
                jururuNavGraph(
                    paddingValues =  paddingValues,
                    onNavigateTwitchChannelClick = twitchNavigationController::navigate
                )

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
    fun navigate(url : String) {
        val isTwitchAppInstalled = runCatching { isInstalledTwitchAppOrException() }.isSuccess
        Intent().apply {
            data = if(isTwitchAppInstalled) {
                Uri.parse(url)
            }else{
                Uri.parse(context.getString(R.string.playStoreTwitchDeepLink))
            }
            context.startActivity(this)
        }
    }

    private fun isInstalledTwitchAppOrException() : PackageInfo {
        return context.run {
            packageManager.getPackageInfo(getString(R.string.twitchPackageName), PackageManager.PackageInfoFlags.of(0L))
        }
    }
}