package com.hegunhee.compose.app

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hegunhee.compose.app.ui.theme.NowInJururuTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun NowInJururuApp(
    jururuAppScaffoldState : JururuAppScaffoldState = rememberJururuAppScaffoldState()
) {
    NowInJururuTheme() {
        Text(text = "JururuApp")
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

}

