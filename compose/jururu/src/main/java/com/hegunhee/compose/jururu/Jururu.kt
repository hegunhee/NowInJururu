package com.hegunhee.compose.jururu

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.hegunhee.ui_component.item.OfflineStream
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun JururuScreenRoot(
    viewModel : JururuViewModel = hiltViewModel(),
    onNavigateTwitchChannelClick : (Context, String) -> Unit
) {
    JururuScreen(
        uiState = viewModel.uiState.value,
        onNavigateTwitchChannelClick
    )
}

@Composable
fun JururuScreen(
    uiState : JururuUiState,
    onNavigateTwitchChannelClick : (Context, String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(LocalPaddingValues.current)) {
        ScreenHeaderText(text = "주르르")
        val context = LocalContext.current
        OfflineStream(
            streamerId = "cotton__123",
            streamerName = "주르르",
            streamerProfileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-300x300.png",
            onTwitchStreamClick = onNavigateTwitchChannelClick,
            onUnfollowButtonClick =  {streamerId -> Toast.makeText(context,"click unfollowButton",
                Toast.LENGTH_SHORT).show()}
        )
        when(uiState) {
            is JururuUiState.Loading -> {
                Text(text = uiState.toString())
            }
            is JururuUiState.Success -> {
                Text(text = uiState.streamerList.toString())

            }
            is JururuUiState.Error -> {
                Text(text = uiState.toString())
            }
        }
    }
}

