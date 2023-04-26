package com.hegunhee.domain.repository

import com.hegunhee.domain.model.StreamDataType

interface Repository {

    suspend fun getStreamData(userId: String = "cotton__123") : Result<StreamDataType>

    suspend fun getJururuStreamData(jururuId : String = "cotton__123") : Result<StreamDataType>
}