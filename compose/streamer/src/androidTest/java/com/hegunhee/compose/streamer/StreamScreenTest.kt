package com.hegunhee.compose.streamer

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class StreamScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenUiStateLoading_whenStreamerScreen_showLoadingScreen() {
        composeTestRule.setContent {
            StreamerScreen(uiModel = StreamerUiModel.Loading,{},{},{})
        }

        composeTestRule
            .onNodeWithText("스트리머")
            .assertIsDisplayed()
    }

    @Test
    fun givenUiStateError_whenStreamerScreen_showErrorScreen() {
        composeTestRule.setContent {
            StreamerScreen(uiModel = StreamerUiModel.Error,{},{},{})
        }

        composeTestRule
            .onNodeWithText("스트리머")
            .assertIsDisplayed()
    }
}
