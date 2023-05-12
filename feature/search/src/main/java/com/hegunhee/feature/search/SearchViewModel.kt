package com.hegunhee.feature.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.model.StreamerData
import com.hegunhee.domain.usecase.GetSearchStreamerDataListUseCase
import com.hegunhee.domain.usecase.InsertStreamerDataUseCase
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

    private val _navigateStreamerTwitch : MutableSharedFlow<String> = MutableSharedFlow()
    val navigateStreamerTwitch : SharedFlow<String> = _navigateStreamerTwitch.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    fun onClickSearchButton() = viewModelScope.launch{
        getSearchDataList(searchQuery.value)
    }

    override fun onClickStreamerItem(streamerLogin : String) {
        viewModelScope.launch {
            _navigateStreamerTwitch.emit(streamerLogin)
        }
    }

    override fun onClickBookMarkStreamer(streamerLogin: String) {
        viewModelScope.launch {
            insertStreamerDataUseCase(StreamerData(streamerLogin))
                .onSuccess {
                    getSearchDataList(searchQuery.value)
                }.onFailure {
                    _toastMessage.emit("저장에 실패했습니다. 잠시후에 시도해주세요")
                }
        }
    }

    private suspend fun getSearchDataList(searchQuery : String) {
        if(searchQuery.isBlank()){
            return
        }
        getSearchStreamerDataListUseCase(searchQuery)
            .onSuccess {
                searchResult.value = it
                isEmptySearchResult.value = it.isEmpty()
            }.onFailure {
                isEmptySearchResult.value = true
            }
    }
}