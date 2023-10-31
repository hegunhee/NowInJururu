package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetBookmarkedStreamDataListUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() : Result<List<StreamDataType>>{
        return repository.getStreamDataList()
    }
}