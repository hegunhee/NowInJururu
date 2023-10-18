package com.hegunhee.data.dataSource.remote

import androidx.paging.PagingData
import com.hegunhee.data.data.json.AuthToken
import com.hegunhee.data.data.json.SearchApiDataResponse
import com.hegunhee.data.data.json.StreamApiDataResponse
import com.hegunhee.data.data.json.StreamerApiDataResponse
import com.hegunhee.domain.model.SearchData
import kotlinx.coroutines.flow.Flow


interface RemoteDataSource {

    suspend fun getAuthToken() : AuthToken

    suspend fun getStreamDataResponse(userLogin : String,token : String) : StreamApiDataResponse

    suspend fun getSearchDataResponse(streamerName : String,token : String) : SearchApiDataResponse

    suspend fun getStreamerDataResponse(vararg streamerLogin : String,token : String) : StreamerApiDataResponse

    suspend fun getGameStreamDataResponse(gameId : String,token : String) : StreamApiDataResponse

    suspend fun getSearchPagingDataResponse(streamerName : String,size : Int) : Flow<PagingData<SearchData>>
}