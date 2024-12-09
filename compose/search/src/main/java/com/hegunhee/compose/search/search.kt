package com.hegunhee.compose.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.hegunhee.compose.search.navigation.LocalPaddingValues
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.ui_component.item.SearchStreamer
import com.hegunhee.ui_component.text.ScreenHeaderText
import com.hegunhee.resource_common.R

@Composable
fun SearchScreenRoot(
    viewModel : SearchViewModel = hiltViewModel(),
    onNavigateTwitchChannelClick : (String) -> Unit
) {
    val (searchQuery, onValueChanged) = viewModel.searchQuery
    val searchResult = viewModel.searchResult.collectAsLazyPagingItems()
    val onFollowButtonClick : ((String) -> Unit) = { streamerId ->
        viewModel.onFollowStreamerClick(streamerId)
        searchResult.refresh()
    }
    SearchScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        searchQuery = searchQuery,
        searchResult = searchResult,
        onValueChanged = onValueChanged,
        onNavigateTwitchChannelClick = onNavigateTwitchChannelClick,
        onSearchStreamDataClick = viewModel::fetchStreamData,
        onFollowButtonClick = onFollowButtonClick
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    uiState : SearchUiState,
    searchQuery : String,
    searchResult : LazyPagingItems<SearchData>,
    onValueChanged : (String) -> Unit,
    onNavigateTwitchChannelClick: (String) -> Unit,
    onSearchStreamDataClick : () -> Unit,
    onFollowButtonClick : (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalPaddingValues.current)
    ) {
        ScreenHeaderText(text = "검색")
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onValueChanged,
            label = { Text("검색어를 입력해주세요") },
            singleLine = true,
            maxLines = 1,
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search_24),
                    contentDescription = null,
                    modifier = Modifier.clickable { onSearchStreamDataClick() }
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchStreamDataClick()
                keyboardController?.hide()
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.header_start_padding))
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.item_top_margin)))
        when(uiState){
            is SearchUiState.Loading -> {

            }
            is SearchUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.item_between_middle))
                ) {
                    items(count = searchResult.itemCount,key = searchResult.itemKey(key = {searchData -> searchData.streamerName})) {index ->
                        val item = searchResult[index]
                        item?.let {  searchData ->
                            SearchStreamer(
                                streamerId = searchData.streamerId,
                                streamerName = searchData.streamerName,
                                profileUrl = searchData.profileUrl,
                                onItemClick = onNavigateTwitchChannelClick,
                                onFollowButtonClick = onFollowButtonClick
                            )
                        }
                    }
                }
            }
            is SearchUiState.Error -> {

            }
        }
    }
}
