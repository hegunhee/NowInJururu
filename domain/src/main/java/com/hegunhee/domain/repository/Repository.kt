package com.hegunhee.domain.repository

import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.model.StreamerData

interface Repository {

    suspend fun getJururuStreamData(jururuId : String = "cotton__123") : Result<StreamDataType>

    suspend fun getStreamDataList() : Result<List<StreamDataType>>

    suspend fun getGameStreamDataList(gameId : String) : Result<List<StreamDataType.OnlineData>>

    suspend fun getSearchStreamerDataList(streamerName : String) : Result<List<SearchData>>

    suspend fun insertStreamer(streamerData: StreamerData) : Result<Unit>

    suspend fun deleteStreamer(streamerData: StreamerData) : Result<Unit>
}