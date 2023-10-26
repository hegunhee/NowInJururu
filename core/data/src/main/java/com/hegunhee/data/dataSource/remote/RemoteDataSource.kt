package com.hegunhee.data.dataSource.remote

import androidx.paging.PagingData
import com.hegunhee.data.data.json.twitch.TwitchAuthToken
import com.hegunhee.data.data.json.twitch.SearchApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamerApiDataResponse
import com.hegunhee.domain.model.SearchData
import kotlinx.coroutines.flow.Flow


interface RemoteDataSource {

    suspend fun getAuthToken() : TwitchAuthToken

    suspend fun getStreamDataResponse(userLogin : String,token : String) : StreamApiDataResponse

    suspend fun getSearchDataResponse(streamerName : String,token : String) : SearchApiDataResponse

    suspend fun getStreamerDataResponse(vararg streamerLogin : String,token : String) : StreamerApiDataResponse

    suspend fun getGameStreamDataResponse(gameId : String,token : String) : StreamApiDataResponse

    suspend fun getSearchPagingDataResponse(streamerName : String,size : Int) : Flow<PagingData<SearchData>>
}