package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetWebSearchDataListUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(query : String,sort : KakaoSearchSortType) : Result<List<KakaoSearchData.Web>>{
        return repository.getWebSearchDataList(query,sort)
    }
}