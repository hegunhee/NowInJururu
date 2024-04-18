package com.hegunhee.maplefinder.searchkakao

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.usecase.kakao.GetKakaoSearchPagingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchKakaoViewModel @Inject constructor(
    private val getKakaoSearchPagingDataUseCase: GetKakaoSearchPagingDataUseCase
) : ViewModel() {

    var uiState : MutableState<SearchUiState> = mutableStateOf(SearchUiState(null))
        private set

    init {
        viewModelScope.launch {
            uiState.value = SearchUiState(getKakaoSearchPagingDataUseCase("주르르",KakaoSearchSortType.Accuracy, KakaoSearchType.Default,30).cachedIn(viewModelScope))
        }
    }
    fun onAction(action : SearchEvent) {
        when(action) {
            SearchEvent.SearchAccuracy -> { searchAccuracy()}
            SearchEvent.SearchRecency -> {searchRecency() }
        }
    }

    private fun searchAccuracy() {
        viewModelScope.launch {
            uiState.value = SearchUiState(getKakaoSearchPagingDataUseCase("주르르",KakaoSearchSortType.Accuracy, KakaoSearchType.Default,30).cachedIn(viewModelScope))
        }
    }

    private fun searchRecency() {
        viewModelScope.launch {
            uiState.value = SearchUiState(getKakaoSearchPagingDataUseCase("주르르",KakaoSearchSortType.Recency, KakaoSearchType.Default,30).cachedIn(viewModelScope))
        }
    }

}