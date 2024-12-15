package com.hegunhee.compose.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hegunhee.compose.search.SearchUiState.Error
import com.hegunhee.compose.search.SearchUiState.Init
import com.hegunhee.compose.search.SearchUiState.Loading
import com.hegunhee.compose.search.SearchUiState.Success
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.usecase.twitch.GetSearchPagingDataUseCase
import com.hegunhee.domain.usecase.twitch.InsertStreamerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _uiState : MutableStateFlow<SearchUiState> = MutableStateFlow(Init)
    val uiState : StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun fetchStreamData(query: String) {
        if(query.isBlank()) return

        viewModelScope.launch {
            _uiState.value = Success(getSearchPagingDataUseCase(query,20).cachedIn(viewModelScope))
        }
    }

    fun onFollowStreamerClick(streamerId : String) = viewModelScope.launch{
        val state = uiState.value
        (state as? Success)?.let {
            insertStreamerDataUseCase(StreamerData(streamerId))
                .onSuccess {
                    _uiState.value = Success(state.twitchPagingData)
                }.onFailure {
                    _uiState.value = Error(it)
                }
        }
    }
}
