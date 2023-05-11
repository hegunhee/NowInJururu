package com.hegunhee.data.dataSource.remote

import com.hegunhee.data.data.json.AuthToken
import com.hegunhee.data.data.json.SearchApiDataResponse
import com.hegunhee.data.data.json.StreamApiDataResponse
import com.hegunhee.data.data.json.StreamerApiDataResponse


interface RemoteDataSource {

    suspend fun getAuthToken() : AuthToken


    suspend fun getStreamDataResponse(userLogin : String,token : String) : StreamApiDataResponse

    suspend fun getSearchDataResponse(streamerName : String,token : String) : SearchApiDataResponse

    suspend fun getStreamerDataResponse(vararg streamerLogin : String,token : String) : StreamerApiDataResponse
}