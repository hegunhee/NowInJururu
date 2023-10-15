package com.hegunhee.data.network

import com.hegunhee.data.data.json.StreamerApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchStreamerDataApi {

    @GET("users")
    suspend fun getStreamerData(
        @Header("Authorization") authorization : String,
        @Query("login") vararg userLogin : String
    ) : StreamerApiDataResponse

}