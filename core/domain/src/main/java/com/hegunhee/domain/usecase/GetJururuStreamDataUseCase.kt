package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetJururuStreamDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() : Result<StreamDataType>{
        return repository.getJururuStreamData()
    }
}