package com.hegunhee.compose.search

import androidx.paging.PagingData
import com.hegunhee.domain.model.twitch.SearchData
import kotlinx.coroutines.flow.Flow


sealed class SearchUiState {

    object Init : SearchUiState()

    object Loading : SearchUiState()

    data class Success(
        val twitchPagingData: Flow<PagingData<SearchData>>
    ) : SearchUiState()

    data class Error(val exception: Throwable) : SearchUiState()
}
