package com.hegunhee.domain.usecase.kakao

import androidx.paging.PagingData
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.repository.kakao.KakaoPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKakaoSearchPagingDataUseCase @Inject constructor(private val kakaoPagingRepository: KakaoPagingRepository) {

    suspend operator fun invoke(query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType, size: Int): Flow<PagingData<KakaoSearchData>> {
        return kakaoPagingRepository.getSearchPagingData(query,sortType,searchType,size)
    }

}
