package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetSearchStreamerDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(streamerId : String) : Result<SearchData> {
        return repository.getSearchStreamerData(streamerId)
    }
}