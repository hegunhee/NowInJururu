package com.hegunhee.compose.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hegunhee.compose.search.SearchUiState.Error
import com.hegunhee.compose.search.SearchUiState.Loading
import com.hegunhee.compose.search.SearchUiState.Success
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.usecase.twitch.GetSearchPagingDataUseCase
import com.hegunhee.domain.usecase.twitch.InsertStreamerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val insertStreamerDataUseCase: InsertStreamerDataUseCase,
    private val getSearchPagingDataUseCase : GetSearchPagingDataUseCase,
) : ViewModel() {

    private val _uiState : MutableStateFlow<SearchUiState> = MutableStateFlow(Loading)
    val uiState : StateFlow<SearchUiState> = _uiState.asStateFlow()

    var searchResult : Flow<PagingData<SearchData>> = emptyFlow()
    private set


    fun fetchStreamData(query: String) {
        if(query.isBlank()) return
        viewModelScope.launch {
            searchResult = getSearchPagingDataUseCase(query,20).cachedIn(viewModelScope)
            _uiState.value = Success
        }
    }

    fun onFollowStreamerClick(streamerId : String) = viewModelScope.launch{
        (uiState.value as? Success)?.let {
            insertStreamerDataUseCase(StreamerData(streamerId))
                .onSuccess {
                    _uiState.value = Success
                }.onFailure {
                    _uiState.value = Error(it)
                }
        }
    }
}
