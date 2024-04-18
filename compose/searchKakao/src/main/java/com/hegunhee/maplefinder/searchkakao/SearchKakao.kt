package com.hegunhee.maplefinder.searchkakao

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun SearchKakaoScreenRoot(
    paddingValues: PaddingValues
) {
    SearchKakaoScreen(
        paddingValues,
        onSearchSortTypeClick = { }
    )
}

@Composable
private fun SearchKakaoScreen(
    paddingValues: PaddingValues,
    onSearchSortTypeClick: (KakaoSearchSortType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        ScreenHeaderText(text = "주르르")
        SearchTypeButtons(onSearchSortTypeClick = onSearchSortTypeClick)
    }
}

@Composable
private fun SearchTypeButtons(
    onSearchSortTypeClick : (KakaoSearchSortType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(onClick = { onSearchSortTypeClick(KakaoSearchSortType.Accuracy) }) {
            Text("기본 검색")
        }
        Button(onClick = { onSearchSortTypeClick(KakaoSearchSortType.Recency)}) {
            Text("최신순")
        }
    }
}