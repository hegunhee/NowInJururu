package com.hegunhee.data.network

import com.hegunhee.data.BuildConfig
import com.hegunhee.data.data.json.StreamApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchStreamDataApi {

    @GET("streams")
    suspend fun getStreamData(
        @Header("client-id") clientId : String = BuildConfig.clientId,
        @Header("Authorization") Authorization : String,
        @Query("user_login") userLogin : String
    ) : StreamApiDataResponse
}