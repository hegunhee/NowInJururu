package com.hegunhee.compose.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.usecase.GetSearchStreamerDataListUseCase
import com.hegunhee.domain.usecase.InsertStreamerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchStreamerDataListUseCase: GetSearchStreamerDataListUseCase,
    private val insertStreamerDataUseCase: InsertStreamerDataUseCase
) : ViewModel() {

    val searchQuery : MutableState<String> = mutableStateOf("")

    private val _uiModel : MutableState<SearchUiModel> = mutableStateOf(SearchUiModel.Loading)
    val uiModel : State<SearchUiModel>
        get() = _uiModel

    fun fetchStreamData() {
        val query = searchQuery.value
        if(query.isBlank()) return
        viewModelScope.launch {
            getSearchStreamerDataListUseCase(query)
                .onSuccess { searchDataList ->
                    _uiModel.value = SearchUiModel.Success(searchDataList)
                }.onFailure {
                    _uiModel.value = SearchUiModel.Error
                }
        }

    }



}