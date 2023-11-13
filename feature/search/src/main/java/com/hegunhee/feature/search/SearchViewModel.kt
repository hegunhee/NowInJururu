package com.hegunhee.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.usecase.GetSearchPagingDataUseCase
import com.hegunhee.domain.usecase.InsertStreamerDataUseCase
import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink
import com.hegunhee.nowinjururu.core.navigation.deeplink.TwitchDeepLinkQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val insertStreamerDataUseCase: InsertStreamerDataUseCase,
    private val getSearchPagingDataUseCase: GetSearchPagingDataUseCase
) : ViewModel(), SearchActionHandler {

    val searchQuery : MutableStateFlow<String> = MutableStateFlow("")

    private var _searchResult : Flow<PagingData<SearchData>> = emptyFlow()
    val searchResult : Flow<PagingData<SearchData>>
        get() = _searchResult

    val isEmptySearchResult : MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val _navigateDeepLink : MutableSharedFlow<DeepLink> = MutableSharedFlow()
    val navigateDeepLink : SharedFlow<DeepLink> = _navigateDeepLink.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    private val _uiEvent : MutableSharedFlow<SearchUiEvent> = MutableSharedFlow<SearchUiEvent>()
    val uiEvent : SharedFlow<SearchUiEvent> = _uiEvent.asSharedFlow()

    fun onClickSearchButton() = viewModelScope.launch{
        getSearchDataList(searchQuery.value)
    }

    override fun onClickStreamerItem(streamerId : String) {
        viewModelScope.launch {
            _navigateDeepLink.emit(DeepLink.Twitch(TwitchDeepLinkQuery.Streamer(streamerId)))
        }
    }

    override fun onClickBookMarkStreamer(streamerId: String) {
        viewModelScope.launch {
            insertStreamerDataUseCase(StreamerData(streamerId))
                .onSuccess {
                    _uiEvent.emit(SearchUiEvent.Refresh)
                }.onFailure {
                    _toastMessage.emit("저장에 실패했습니다. 잠시후에 시도해주세요")
                }
        }
    }

    private suspend fun getSearchDataList(searchQuery : String) {
        if(searchQuery.isBlank()){
            viewModelScope.launch {
                _toastMessage.emit("글자를 입력해주세요")
            }
            return
        }
        _searchResult = getSearchPagingDataUseCase(searchQuery,20).cachedIn(viewModelScope)
    }
}