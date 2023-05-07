package com.hegunhee.feature.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.usecase.GetSearchStreamerDataListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchStreamerDataListUseCase: GetSearchStreamerDataListUseCase) : ViewModel(), SearchActionHandler {

    val searchQuery : MutableStateFlow<String> = MutableStateFlow("")

    val searchResult : MutableStateFlow<List<SearchData>> = MutableStateFlow(emptyList())

    val isEmptySearchResult : MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onClickSearchButton() = viewModelScope.launch{
        if(searchQuery.value.isBlank()){
            return@launch
        }
        getSearchStreamerDataListUseCase(searchQuery.value)
            .onSuccess {
                searchResult.value = it
                isEmptySearchResult.value = it.isEmpty()
            }.onFailure {
                isEmptySearchResult.value = true
            }
    }

    override fun onClickStreamerItem(streamerLogin : String) {

    }
}