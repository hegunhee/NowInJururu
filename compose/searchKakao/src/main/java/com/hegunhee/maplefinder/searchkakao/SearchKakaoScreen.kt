package com.hegunhee.maplefinder.searchkakao

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink
import com.hegunhee.nowinjururu.core.navigation.deeplink.handleDeepLink
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
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.deepLink) {
        viewModel.deepLink.collect{
            context.handleDeepLink(it)
        }
    }
}

@Composable
private fun SearchKakaoScreen(
    paddingValues: PaddingValues,
    uiState : SearchKakaoUiState,
    onAction : (SearchUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        ScreenHeaderText(text = "주르르")
        SearchTypeButtons(onSearchSortTypeClick = onAction)
        when(uiState) {
            is SearchKakaoUiState.Loading -> {}
            is SearchKakaoUiState.Success -> {
                val pagingData = uiState.kakaoPagingData.collectAsLazyPagingItems()
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.item_between_middle))
                ) {
                    items(pagingData.itemCount) { index ->
                        val item = pagingData[index]
                        item?.let {
                            KakaoSearchItem(
                                kakaoSearchData = it,
                                onAction = onAction
                            )
                        }
                    }
                }
            }
            is SearchKakaoUiState.Error -> {}
        }
    }
}

@Composable
private fun SearchTypeButtons(
    onSearchSortTypeClick : (SearchUiEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(onClick = { onSearchSortTypeClick(SearchUiEvent.SearchUiAccuracy) }) {
            Text("기본 검색")
        }
        Button(onClick = { onSearchSortTypeClick(SearchUiEvent.SearchUiAccuracy)}) {
            Text("최신순")
        }
    }
}

@Composable
private fun KakaoSearchItem(
    kakaoSearchData: KakaoSearchData,
    onAction: (SearchUiEvent) -> Unit
) {
    when(kakaoSearchData) {
        is KakaoSearchData.Image -> {
            KakaoImage(
                kakaoImageData = kakaoSearchData,
                onAction = onAction
            )
        }
        is KakaoSearchData.Video ->{
            KakaoVideo(
                kakaoVideoData = kakaoSearchData,
                onAction = onAction
            )
        }
        is KakaoSearchData.Web -> {
            KakaoWeb(
                kakaoWebData = kakaoSearchData,
                onAction = onAction
            )
        }
    }
}

@Composable
private fun KakaoImage(
    kakaoImageData : KakaoSearchData.Image,
    onAction: (SearchUiEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAction(SearchUiEvent.WebLinkClick(DeepLink.Kakao(kakaoImageData.url))) }
    ) {
        AsyncImage(
            model = kakaoImageData.imageUrl,
            contentDescription = kakaoImageData.url,
            modifier = Modifier.size(130.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column {
            Text(text = kakaoImageData.displaySiteName, color = Color.Black)
            Text(text = kakaoImageData.dateTime,color = Color.Gray)
            ShareImage(onShareButtonClick = onAction, url = kakaoImageData.url,kakaoImageData.displaySiteName)
        }
    }
}

@Composable
private fun KakaoVideo(
    kakaoVideoData : KakaoSearchData.Video,
    onAction: (SearchUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAction(SearchUiEvent.WebLinkClick(DeepLink.Kakao(kakaoVideoData.url))) }
    ) {
        AsyncImage(model = kakaoVideoData.thumbNailUrl, contentDescription = kakaoVideoData.thumbNailUrl,modifier = Modifier
            .fillMaxWidth()
            .size(137.dp, 78.dp))
        ShareImage(onAction,kakaoVideoData.url,kakaoVideoData.title)
        Text(text = kakaoVideoData.title, fontSize = 20.sp)
        Text(text = kakaoVideoData.getVideoInfo(),fontSize = 13.sp)
    }
}

@Composable
private fun KakaoWeb(
    kakaoWebData : KakaoSearchData.Web,
    onAction: (SearchUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAction(SearchUiEvent.WebLinkClick(DeepLink.Kakao(kakaoWebData.url))) }
    ) {
        Text(text = kakaoWebData.getUrlDomain(), fontSize = 15.sp,color = Color.LightGray)
        Text(text = kakaoWebData.title.toHtmlText(),fontSize = 20.sp)
        Text(text = kakaoWebData.dateTime,fontSize = 15.sp,color = Color.LightGray)
        ShareImage(onShareButtonClick = onAction, url = kakaoWebData.url,kakaoWebData.title)
        Text(text = kakaoWebData.contents.toHtmlText())
    }
}

@Composable
private fun ShareImage(
    onShareButtonClick: (SearchUiEvent) -> Unit,
    url : String,
    title : String
) {
    Image(painter = painterResource(id = R.drawable.ic_share_24),
        contentDescription = "공유하기",
        modifier = Modifier
            .clickable { onShareButtonClick(SearchUiEvent.ShareClick(DeepLink.Share(url,title))) }
            .size(30.dp)
    )
}

private fun String.toHtmlText() : String = Jsoup.parse(this).text()
