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
        @Header("Authorization") authorization : String,
        @Query("user_login") userLogin : String
    ) : StreamApiDataResponse


    /**
     * game_id = String타입의 숫자 "19976"
     * type : "all" (default), "live"
     * language : "ko"
     * vararg 가능
     *
     * ps) game_name는 게임의 이름 : "mapleStory"
     */
    @GET("streams")
    suspend fun getGameStreamData(
        @Header("client-id") clientId: String = BuildConfig.clientId,
        @Header("Authorization") authorization: String,
        @Query("game_id") gameId: String,
        @Query("type") type: String = "live",
        @Query("language") vararg language: String = arrayOf("ko")
    ) : StreamApiDataResponse
}