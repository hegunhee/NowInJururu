package com.hegunhee.maplefinder.searchkakao

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.resource_common.R
import com.hegunhee.ui_component.text.ScreenHeaderText
import org.jsoup.Jsoup

@Composable
fun SearchKakaoScreenRoot(
    paddingValues: PaddingValues,
    viewModel : SearchKakaoViewModel = hiltViewModel()
) {
    SearchKakaoScreen(
        paddingValues,
        uiState = viewModel.uiState.value,
        onAction = viewModel::onAction
    )
}

@Composable
private fun SearchKakaoScreen(
    paddingValues: PaddingValues,
    uiState : SearchUiState,
    onAction : (SearchEvent) -> Unit
) {
    val pagingData = uiState.kakaoPagingData?.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        ScreenHeaderText(text = "주르르")
        SearchTypeButtons(onSearchSortTypeClick = onAction)
        if(pagingData != null) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.item_between_middle))
            ) {
                items(pagingData.itemCount) { index ->
                    val item = pagingData[index]
                    item?.let {
                        KakaoSearchItem(
                            kakaoSearchData = it,
                            onClickItem = {},
                            onShareButtonClick = { }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchTypeButtons(
    onSearchSortTypeClick : (SearchEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(onClick = { onSearchSortTypeClick(SearchEvent.SearchAccuracy) }) {
            Text("기본 검색")
        }
        Button(onClick = { onSearchSortTypeClick(SearchEvent.SearchAccuracy)}) {
            Text("최신순")
        }
    }
}

@Composable
private fun KakaoSearchItem(
    kakaoSearchData: KakaoSearchData,
    onClickItem : (String) -> Unit,
    onShareButtonClick : (String) -> Unit
) {
    when(kakaoSearchData) {
        is KakaoSearchData.Image -> {
            KakaoImage(
                kakaoImageData = kakaoSearchData,
                onClickItem = onClickItem,
                onShareButtonClick = onShareButtonClick
            )
        }
        is KakaoSearchData.Video ->{
            KakaoVideo(
                kakaoVideoData = kakaoSearchData,
                onClickItem = onClickItem,
                onShareButtonClick = onShareButtonClick
            )
        }
        is KakaoSearchData.Web -> {
            KakaoWeb(
                kakaoWebData = kakaoSearchData,
                onClickItem = onClickItem,
                onShareButtonClick = onShareButtonClick
            )
        }
    }
}

@Composable
private fun KakaoImage(
    kakaoImageData : KakaoSearchData.Image,
    onClickItem : (String) -> Unit,
    onShareButtonClick : (String) -> Unit
) {
    Text(text = kakaoImageData.toString())
}

@Composable
private fun KakaoVideo(
    kakaoVideoData : KakaoSearchData.Video,
    onClickItem : (String) -> Unit,
    onShareButtonClick : (String) -> Unit
) {
    Text(text = kakaoVideoData.toString())
}

@Composable
private fun KakaoWeb(
    kakaoWebData : KakaoSearchData.Web,
    onClickItem : (String) -> Unit,
    onShareButtonClick : (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable { onClickItem(kakaoWebData.url) }
    ) {
        Text(text = kakaoWebData.getUrlDomain(), fontSize = 15.sp,color = Color.LightGray)
        Text(text = kakaoWebData.title.toHtmlText(),fontSize = 20.sp)
        Text(text = kakaoWebData.dateTime,fontSize = 15.sp,color = Color.LightGray)
        Image(painter = painterResource(id = R.drawable.ic_share_24),
            contentDescription = "공유하기",
            modifier = Modifier.clickable { onShareButtonClick(kakaoWebData.url) }.size(30.dp)
        )
        Text(text = kakaoWebData.contents.toHtmlText())
    }
}

private fun String.toHtmlText() : String = Jsoup.parse(this).text()