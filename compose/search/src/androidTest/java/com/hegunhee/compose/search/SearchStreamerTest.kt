package com.hegunhee.compose.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.hegunhee.domain.model.twitch.SearchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class SearchStreamerTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenSearchItem_whenStreamerScreen_showSearchStreamerName() {
        val query = "주르르"
        val streamerId = "cotton__123"
        composeTestRule.setContent {
            val pagingItems = createPagingData(streamerId,query).collectAsLazyPagingItems()
            SearchScreen(uiState = SearchUiState.Success, searchQuery = query, searchResult = pagingItems,{},{},{},{})
        }

        composeTestRule
            .onNodeWithText(streamerId)
            .assertIsDisplayed()

    }

    private fun createPagingData(streamerId: String,query: String): Flow<PagingData<SearchData>> {
        return flowOf(
            PagingData.from(
                listOf(
                    SearchData(
                        streamerId,
                        query,
                        "",
                        false,
                        emptyList(),
                        "",
                        ""
                    )
                )
            )
        )
    }

}
