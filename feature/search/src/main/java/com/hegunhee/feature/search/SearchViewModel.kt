package com.hegunhee.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.model.StreamerData
import com.hegunhee.domain.usecase.GetSearchStreamerDataListUseCase
import com.hegunhee.domain.usecase.InsertStreamerDataUseCase
import com.hegunhee.feature.common.twitch.TwitchDeepLink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchStreamerDataListUseCase: GetSearchStreamerDataListUseCase,
    private val insertStreamerDataUseCase: InsertStreamerDataUseCase
) : ViewModel(), SearchActionHandler {

    val searchQuery : MutableStateFlow<String> = MutableStateFlow("")

    val searchResult : MutableStateFlow<List<SearchData>> = MutableStateFlow(emptyList())

    val isEmptySearchResult : MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val _navigateStreamerTwitch : MutableSharedFlow<TwitchDeepLink> = MutableSharedFlow()
    val navigateStreamerTwitch : SharedFlow<TwitchDeepLink> = _navigateStreamerTwitch.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    private val _isBookMarkSuccess : MutableSharedFlow<Unit> = MutableSharedFlow<Unit>()
    val isBookMarkSuccess : SharedFlow<Unit> =  _isBookMarkSuccess.asSharedFlow()

    private val _isLoading : MutableSharedFlow<Boolean> = MutableSharedFlow<Boolean>()
    val isLoading : SharedFlow<Boolean> = _isLoading.asSharedFlow()

    fun onClickSearchButton() = viewModelScope.launch{
        getSearchDataList(searchQuery.value)
    }

    override fun onClickStreamerItem(streamerId : String) {
        viewModelScope.launch {
            _navigateStreamerTwitch.emit(TwitchDeepLink.Streamer(streamerId = streamerId))
        }
    }

    override fun onClickBookMarkStreamer(streamerId: String) {
        viewModelScope.launch {
            insertStreamerDataUseCase(StreamerData(streamerId))
                .onSuccess {
                    getSearchDataList(searchQuery.value)
                    _isBookMarkSuccess.emit(Unit)
                }.onFailure {
                    _toastMessage.emit("저장에 실패했습니다. 잠시후에 시도해주세요")
                }
        }
    }

    private suspend fun getSearchDataList(searchQuery : String) {
        if(searchQuery.isBlank()){
            return
        }
        _isLoading.emit(true)
        getSearchStreamerDataListUseCase(searchQuery)
            .onSuccess {
                searchResult.value = it
                isEmptySearchResult.value = it.isEmpty()
                _isLoading.emit(false)
            }.onFailure {
                isEmptySearchResult.value = true
                _isLoading.emit(false)
            }
    }
}