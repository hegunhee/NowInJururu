package com.hegunhee.compose.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.usecase.GetSearchPagingDataUseCase
import com.hegunhee.domain.usecase.InsertStreamerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val insertStreamerDataUseCase: InsertStreamerDataUseCase,
    private val getSearchPagingDataUseCase : GetSearchPagingDataUseCase,
) : ViewModel() {

    val searchQuery : MutableState<String> = mutableStateOf("")

    private val _uiModel : MutableState<SearchUiModel> = mutableStateOf(SearchUiModel.Loading)
    val uiModel : State<SearchUiModel>
        get() = _uiModel

    var searchResult : Flow<PagingData<SearchData>> = emptyFlow()
    private set


    fun fetchStreamData() {
        val query = searchQuery.value
        if(query.isBlank()) return
        viewModelScope.launch {
            searchResult = getSearchPagingDataUseCase(query,20).cachedIn(viewModelScope)
            _uiModel.value = SearchUiModel.Success
        }
    }

    fun onFollowStreamerClick(streamerId : String) = viewModelScope.launch{
        (uiModel.value as? SearchUiModel.Success)?.let {
            insertStreamerDataUseCase(StreamerData(streamerId))
                .onSuccess {
                    _uiModel.value = SearchUiModel.Success
                }.onFailure {
                    _uiModel.value = SearchUiModel.Error
                }
        }
    }
}