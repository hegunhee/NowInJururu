package com.hegunhee.domain.Test

import com.hegunhee.domain.model.StreamDataType
import javax.inject.Inject


class TestUseCase @Inject constructor(private val repository: TestRepository){

    suspend operator fun invoke() : Result<StreamDataType> {
        return repository.getStreamData()
    }
}