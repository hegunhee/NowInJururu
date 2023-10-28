package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetStreamDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(streamerId : String) : Result<StreamDataType>{
        return repository.getStreamData(streamerId)
    }
}