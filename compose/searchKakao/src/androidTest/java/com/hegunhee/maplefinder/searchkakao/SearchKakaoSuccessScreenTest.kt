package com.hegunhee.maplefinder.searchkakao

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.ui_component.style.AccuracyText
import com.hegunhee.ui_component.style.BottomSheetTitle.KakaoSearchTitle
import com.hegunhee.ui_component.style.RecencyText
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class SearchKakaoSuccessScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun given_whenDefaultScreen_showHeaderTestAndTypeButtons() {
        composeTestRule.setContent {
            SearchKakaoScreen(paddingValues = PaddingValues(0.dp),SearchKakaoUiState.Init,"",{},{})
        }

        composeTestRule
            .onNodeWithText(KakaoSearchTitle)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(AccuracyText)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(RecencyText)
            .assertIsDisplayed()
    }

    @Test
    fun givenPagingData_whenSuccessScreen_showKakaoSearchData() {
        val query = "주르르"
        val kakaoSearchDataList = createKakaoSearchDataList(query)
        val pagingData = flowOf(PagingData.from(kakaoSearchDataList))
        val successUiState = SearchKakaoUiState.Success(query,pagingData)
        composeTestRule.setContent {
            SearchKakaoScreen(PaddingValues(0.dp),successUiState,query,{},{})
        }

        for(searchData in kakaoSearchDataList) {
            when(searchData) {
                is KakaoSearchData.Web -> {
                    composeTestRule
                        .onNodeWithText(searchData.title)
                        .assertIsDisplayed()
                }
                is KakaoSearchData.Video -> {
                    composeTestRule
                        .onNodeWithText(searchData.getVideoInfo())
                        .assertIsDisplayed()
                }
                is KakaoSearchData.Image -> {
                    composeTestRule
                        .onNodeWithText(searchData.displaySiteName)
                        .assertIsDisplayed()
                }
            }
        }
    }

    private fun createKakaoSearchDataList(query: String): List<KakaoSearchData> {
        return listOf(
            KakaoSearchData.Web(
                contents = query + "내용",
                dateTime = "",
                title = query + "타이틀",
                url = "https://www.naver.com",
            ),
            KakaoSearchData.Image(
                sourceType = "",
                dateTime = "",
                displaySiteName = "$query 사이트",
                url = "https://www.naver.com",
                imageUrl = "",
                thumbNailUrl = "",
                width = 100,
                height = 100,
            ),
            KakaoSearchData.Video(
                author = "$query 지음",
                dateTime = "",
                playTime = 0,
                thumbNailUrl = "",
                title = "",
                url = ""
            )
        )
    }

}
