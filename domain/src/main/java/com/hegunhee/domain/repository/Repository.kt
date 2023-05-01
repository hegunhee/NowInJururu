package com.hegunhee.domain.repository

import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.model.StreamDataType

interface Repository {

    suspend fun getStreamData(userLogin: String = "cotton__123") : Result<StreamDataType>

    suspend fun getJururuStreamData(jururuId : String = "mawang0216") : Result<StreamDataType>

    suspend fun getSearchStreamerDataList(streamerName : String) : Result<List<SearchData>>
}