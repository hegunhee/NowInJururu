package com.hegunhee.compose.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.hegunhee.compose.search.navigation.LocalPaddingValues
import com.hegunhee.ui_component.item.SearchStreamer
import com.hegunhee.ui_component.text.ScreenHeaderText
import com.hegunhee.resource_common.R
import com.hegunhee.ui_component.item.SearchBar
import com.hegunhee.ui_component.screen.ErrorScreen
import com.hegunhee.ui_component.screen.LoadingScreen
import com.hegunhee.ui_component.style.BottomSheetTitle.TwitchSearchTitle

@Composable
fun SearchScreenRoot(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val (searchQuery, onQueryChanged) = rememberSaveable { mutableStateOf("") }
    SearchScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        searchQuery = searchQuery,
        onQueryChanged = onQueryChanged,
        onSearchStreamDataClick = viewModel::fetchStreamData,
        onFollowButtonClick = viewModel::onFollowStreamerClick,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    onSearchStreamDataClick: (String) -> Unit,
    onFollowButtonClick: (String) -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalPaddingValues.current)
    ) {
        ScreenHeaderText(text = TwitchSearchTitle)
        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChanged = onQueryChanged,
            onClickSearch = onSearchStreamDataClick,
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.item_top_margin)))
        when (uiState) {
            is SearchUiState.Init -> {

            }

            is SearchUiState.Loading -> {
                LoadingScreen()
            }

            is SearchUiState.Success -> {
                val pagingData = uiState.twitchPagingData.collectAsLazyPagingItems()
                val onFollowAndRefreshClick: (String) -> Unit = { streamerId ->
                    onFollowButtonClick(streamerId)
                    pagingData.refresh()
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.item_between_middle))
                ) {
                    items(
                        count = pagingData.itemCount,
                        key = pagingData.itemKey(key = { searchData -> searchData.streamerName })
                    ) { index ->
                        val item = pagingData[index]
                        item?.let { searchData ->
                            SearchStreamer(
                                platform = searchData.platform,
                                streamerId = searchData.streamerId,
                                streamerName = searchData.streamerName,
                                profileUrl = searchData.profileUrl,
                                onFollowButtonClick = onFollowAndRefreshClick,
                                context = context,
                            )
                        }
                    }
                }
            }

            is SearchUiState.Error -> {
                ErrorScreen(exception = uiState.exception, onRetryClick = null)
            }
        }
    }
}
