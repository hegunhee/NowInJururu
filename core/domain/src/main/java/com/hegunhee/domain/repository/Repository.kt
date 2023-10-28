package com.hegunhee.domain.repository

import androidx.paging.PagingData
import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.model.StreamerData
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getStreamData(streamerId : String) : Result<StreamDataType>

    suspend fun getStreamDataList() : Result<List<StreamDataType>>

    suspend fun getGameStreamDataList(gameId : String) : Result<List<StreamDataType.OnlineData>>

    suspend fun getSearchStreamerDataList(streamerName : String) : Result<List<SearchData>>

    suspend fun insertStreamer(streamerData: StreamerData) : Result<Unit>

    suspend fun deleteStreamer(streamerData: StreamerData) : Result<Unit>

    suspend fun searchPagingSource(streamerName : String,size : Int) : Flow<PagingData<SearchData>>
}