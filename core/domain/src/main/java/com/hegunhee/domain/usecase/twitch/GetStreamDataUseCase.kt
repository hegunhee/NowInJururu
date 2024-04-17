package com.hegunhee.domain.usecase.twitch

import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetStreamDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(streamerId : String) : Result<StreamDataType>{
        return repository.getStreamData(streamerId)
    }
}