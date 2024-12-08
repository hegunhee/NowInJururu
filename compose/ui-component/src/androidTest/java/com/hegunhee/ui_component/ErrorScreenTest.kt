package com.hegunhee.ui_component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.hegunhee.ui_component.screen.ErrorScreen
import com.hegunhee.ui_component.style.ErrorMessage
import com.hegunhee.ui_component.style.RetryMessage
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun given_whenErrorScreen_showErrorMessage() {
        composeTestRule.setContent {
            ErrorScreen(
                exception = IllegalStateException(),
                onRetryClick = null,
            )
        }

        composeTestRule
            .onNodeWithText(ErrorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun givenOnRetryClickListener_whenErrorScreen_showRetryMessage() {
        composeTestRule.setContent {
            ErrorScreen(
                exception = IllegalStateException(),
                onRetryClick = { },
            )
        }

        composeTestRule
            .onNodeWithText(RetryMessage)
            .assertIsDisplayed()
    }
}
