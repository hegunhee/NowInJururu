package com.hegunhee.domain.repository

import androidx.paging.PagingData
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getKakaoSearchPagingData(query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType, size: Int) : Flow<PagingData<KakaoSearchData>>
}