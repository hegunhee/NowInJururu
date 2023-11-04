package com.hegunhee.domain.usecase

import androidx.paging.PagingData
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKakaoSearchPagingDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType?, size: Int): Flow<PagingData<KakaoSearchData>> {
        return repository.getKakaoSearchPagingData(query,sortType,searchType,size)
    }

}