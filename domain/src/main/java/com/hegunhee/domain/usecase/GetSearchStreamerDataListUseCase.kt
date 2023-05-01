package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetSearchStreamerDataListUseCase @Inject constructor(private val repository : Repository) {

    suspend operator fun invoke(streamerName : String) : Result<List<SearchData>> {
        return repository.getSearchStreamerDataList(streamerName)
    }
}