package com.hegunhee.domain.Test

import com.hegunhee.domain.model.StreamDataType

interface TestRepository {

    suspend fun getStreamData(userLogin: String = "cotton__123") : Result<StreamDataType>
}