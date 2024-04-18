package com.hegunhee.maplefinder.searchkakao

import androidx.paging.PagingData
import com.hegunhee.domain.model.kakao.KakaoSearchData
import kotlinx.coroutines.flow.Flow

data class SearchUiState(
    val kakaoPagingData : Flow<PagingData<KakaoSearchData>>?
)