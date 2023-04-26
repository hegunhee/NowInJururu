package com.hegunhee.data.dataSource.remote

import com.hegunhee.data.data.json.AuthToken
import com.hegunhee.data.data.json.StreamApiDataResponse


interface RemoteDataSource {

    suspend fun getAuthToken() : AuthToken


    suspend fun getStreamDataResponse(userLogin : String,token : String) : StreamApiDataResponse
}