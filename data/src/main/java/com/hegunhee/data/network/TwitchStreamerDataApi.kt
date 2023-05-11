package com.hegunhee.data.network

import com.hegunhee.data.BuildConfig
import com.hegunhee.data.data.json.StreamerApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchStreamerDataApi {

    @GET("users")
    suspend fun getStreamerData(
        @Header("client-id") clientId : String = BuildConfig.clientId,
        @Header("Authorization") authorization : String,
        @Query("login") vararg userLogin : String
    ) : StreamerApiDataResponse

}