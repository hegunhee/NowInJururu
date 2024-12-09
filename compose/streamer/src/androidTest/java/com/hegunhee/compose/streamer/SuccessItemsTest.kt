package com.hegunhee.compose.streamer

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.ui_component.style.followCancelMessage
import org.junit.Rule
import org.junit.Test

class SuccessItemsTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenOnlineItem_whenStreamerScreen_showOnlineTextStreamerName() {
        val streamerName = "주르르"
        composeTestRule.setContent {
            StreamerScreen(uiState = StreamerUiState.Success(listOf(createOnlineStreamItem(streamerName))),{},{},{})
        }

        composeTestRule
            .onNodeWithText("온라인")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(streamerName)
            .assertIsDisplayed()
    }

    @Test
    fun givenOfflineItem_whenStreamerScreen_showOfflineTextAndStreamerName() {
        val streamerName = "주르르"
        composeTestRule.setContent {
            StreamerScreen(uiState = StreamerUiState.Success(listOf(createOfflineStreamItem(streamerName))),{},{},{})
        }

        composeTestRule
            .onNodeWithText("오프라인")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(streamerName)
            .assertIsDisplayed()
    }

    @Test
    fun givenGameStream_whenStreamerScreen_showRecommendTextAndGameName() {
        val gameName = "메이플스토리"
        composeTestRule.setContent {
            StreamerScreen(uiState = StreamerUiState.Success(listOf(createGameStreamItem(gameName))),{},{},{})
        }

        composeTestRule
            .onNodeWithText("추천 생방송 채널")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(gameName)
            .assertIsDisplayed()
    }

    @Test
    fun givenOfflineStreamer_whenClickMoreButton_showUnfollowBottomSheet() {
        val streamerName = "주르르"
        composeTestRule.setContent {
            StreamerScreen(uiState = StreamerUiState.Success(listOf(createOfflineStreamItem(streamerName))),{},{},{})
        }

        composeTestRule
            .onNodeWithContentDescription("more Button")
            .performClick()

        composeTestRule
            .onNodeWithText(followCancelMessage(""))
            .assertIsDisplayed()
    }

    private fun createOnlineStreamItem(streamerName: String):StreamItem {
        return StreamItem.Online(listOf(createOnlineStreamData(streamerName = streamerName)))
    }

    private fun createOfflineStreamItem(streamerName: String): StreamItem {
        return StreamItem.Offline(listOf(StreamDataType.OfflineData("",streamerName,"")))
    }

    private fun createGameStreamItem(gameName: String): StreamItem {
        return StreamItem.Recommend(gameName,listOf(createOnlineStreamData(gameName = gameName)))
    }

    private fun createOnlineStreamData(streamerName: String = "", gameName: String = ""): StreamDataType.OnlineData {
        return StreamDataType.OnlineData("",gameName,"",false,"","", emptyList(),"","","","","","",streamerName,"")
    }

}
