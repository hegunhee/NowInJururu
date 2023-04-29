package com.hegunhee.data.network

import com.hegunhee.data.BuildConfig
import com.hegunhee.data.data.json.SearchApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchSearchDataApi {

    @GET("search/channels")
    suspend fun getSearchData(
        @Header("client-id") clientId : String = BuildConfig.clientId,
        @Header("Authorization") authorization : String,
        @Query("query") streamerName : String,
        @Query("live_only") liveOnly : Boolean = false,
    ) : SearchApiDataResponse
}