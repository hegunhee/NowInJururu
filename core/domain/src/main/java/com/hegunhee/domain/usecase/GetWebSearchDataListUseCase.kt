package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoWebData
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetWebSearchDataListUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(query : String,sort : KakaoSearchSortType) : Result<List<KakaoWebData>>{
        return repository.getWebSearchDataList(query,sort)
    }
}