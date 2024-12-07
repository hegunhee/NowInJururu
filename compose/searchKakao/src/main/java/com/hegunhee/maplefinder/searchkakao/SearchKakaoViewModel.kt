package com.hegunhee.maplefinder.searchkakao

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.usecase.kakao.GetKakaoSearchPagingDataUseCase
import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchKakaoViewModel @Inject constructor(
    private val getKakaoSearchPagingDataUseCase: GetKakaoSearchPagingDataUseCase
) : ViewModel() {

    var uiState : MutableState<SearchKakaoUiState> = mutableStateOf(SearchKakaoUiState.Loading)
        private set

    private val _deepLink : MutableSharedFlow<DeepLink> = MutableSharedFlow()
    val deepLink : SharedFlow<DeepLink> = _deepLink.asSharedFlow()

    init {
        viewModelScope.launch {
            uiState.value = SearchKakaoUiState.Success(getKakaoSearchPagingDataUseCase("주르르",KakaoSearchSortType.Accuracy, KakaoSearchType.Default,30).cachedIn(viewModelScope))
        }
    }
    fun onAction(action : SearchUiEvent) {
        when(action) {
            SearchUiEvent.SearchUiAccuracy -> { searchAccuracy()}
            SearchUiEvent.SearchUiRecency -> {searchRecency() }
            is SearchUiEvent.ShareClick -> {
                shareClick(action.deepLink)
            }
            is SearchUiEvent.WebLinkClick -> {
                webLinkClick(action.deepLink)
            }
        }
    }

    private fun searchAccuracy() {
        viewModelScope.launch {
            uiState.value = SearchKakaoUiState.Success(getKakaoSearchPagingDataUseCase("주르르",KakaoSearchSortType.Accuracy, KakaoSearchType.Default,30).cachedIn(viewModelScope))
        }
    }

    private fun searchRecency() {
        viewModelScope.launch {
            uiState.value = SearchKakaoUiState.Success(getKakaoSearchPagingDataUseCase("주르르",KakaoSearchSortType.Recency, KakaoSearchType.Default,30).cachedIn(viewModelScope))
        }
    }

    private fun shareClick(deepLink : DeepLink.Share) {
        viewModelScope.launch {
            _deepLink.emit(deepLink)
        }
    }

    private fun webLinkClick(deepLink : DeepLink.Kakao) {
        viewModelScope.launch {
            _deepLink.emit(deepLink)
        }
    }
}