package com.hegunhee.maplefinder.searchkakao

import androidx.paging.PagingData
import com.hegunhee.domain.model.kakao.KakaoSearchData
import kotlinx.coroutines.flow.Flow

sealed interface SearchKakaoUiState {

    object Loading : SearchKakaoUiState

    data class Success(
        val kakaoPagingData: Flow<PagingData<KakaoSearchData>>
    ) : SearchKakaoUiState

    data class Error(val exception: Throwable) : SearchKakaoUiState
}
