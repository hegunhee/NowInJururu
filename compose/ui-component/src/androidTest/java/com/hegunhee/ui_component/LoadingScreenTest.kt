package com.hegunhee.ui_component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.hegunhee.ui_component.screen.LoadingScreen
import com.hegunhee.ui_component.style.LoadingMessage
import org.junit.Rule
import org.junit.Test

class LoadingScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun given_whenLoadingScreen_showLoadingMessage() {
        composeTestRule.setContent {
            LoadingScreen()
        }

        composeTestRule
            .onNodeWithText(LoadingMessage)
            .assertIsDisplayed()
    }

}
