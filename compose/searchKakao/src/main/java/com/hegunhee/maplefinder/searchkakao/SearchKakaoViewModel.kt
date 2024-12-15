package com.hegunhee.maplefinder.searchkakao

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.usecase.kakao.GetKakaoSearchPagingDataUseCase
import com.hegunhee.nowinjururu.core.navigation.deeplink.type.DeepLink
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

    var uiState: MutableState<SearchKakaoUiState> = mutableStateOf(SearchKakaoUiState.Init)
        private set

    private val _deepLink: MutableSharedFlow<DeepLink> = MutableSharedFlow()
    val deepLink: SharedFlow<DeepLink> = _deepLink.asSharedFlow()

    fun onAction(action: SearchKakaoUiEvent) = viewModelScope.launch {
        when (action) {
            is SearchKakaoUiEvent.Search -> {
                search(action.query)
            }

            SearchKakaoUiEvent.SearchTypeAccuracy -> {
                searchAccuracy()
            }

            SearchKakaoUiEvent.SearchTypeRecency -> {
                searchRecency()
            }

            is SearchKakaoUiEvent.ShareClick -> {
                shareClick(action.deepLink)
            }

            is SearchKakaoUiEvent.WebLinkClick -> {
                webLinkClick(action.deepLink)
            }
        }
    }

    private fun search(query: String) {
        viewModelScope.launch {
            uiState.value = SearchKakaoUiState.Success(
                query,
                getKakaoSearchPagingDataUseCase(
                    query,
                    KakaoSearchSortType.DEFAULT,
                    KakaoSearchType.Default,
                    30
                ).cachedIn(viewModelScope)
            )
        }
    }

    private fun searchAccuracy() {
        viewModelScope.launch {
            val state = uiState.value
            if (state is SearchKakaoUiState.Success) {
                uiState.value = SearchKakaoUiState.Success(
                    state.query,
                    getKakaoSearchPagingDataUseCase(
                        state.query,
                        KakaoSearchSortType.Accuracy,
                        KakaoSearchType.Default,
                        30
                    ).cachedIn(viewModelScope)
                )
            }
        }
    }

    private fun searchRecency() {
        viewModelScope.launch {
            val state = uiState.value
            if (state is SearchKakaoUiState.Success) {
                uiState.value = SearchKakaoUiState.Success(
                    state.query,
                    getKakaoSearchPagingDataUseCase(
                        state.query,
                        KakaoSearchSortType.Recency,
                        KakaoSearchType.Default,
                        30
                    ).cachedIn(viewModelScope)
                )
            }
        }
    }

    private fun shareClick(deepLink: DeepLink.Share) {
        viewModelScope.launch {
            _deepLink.emit(deepLink)
        }
    }

    private fun webLinkClick(deepLink: DeepLink.Kakao) {
        viewModelScope.launch {
            _deepLink.emit(deepLink)
        }
    }
}
