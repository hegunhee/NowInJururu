package com.hegunhee.compose.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun SearchScreenRoot(
    onNavigateTwitchChannelClick : (String) -> Unit
) {
    SearchScreen(onNavigateTwitchChannelClick = onNavigateTwitchChannelClick)
}

@Composable
fun SearchScreen(
    onNavigateTwitchChannelClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(LocalPaddingValues.current)) {
        ScreenHeaderText(text = "검색")
    }
}