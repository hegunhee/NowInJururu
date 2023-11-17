package com.hegunhee.data.network

import com.hegunhee.data.data.json.twitch.SearchApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamerApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchService {

    @GET("search/channels")
    suspend fun getSearchData(
        @Header("Authorization") authorization : String,
        @Query("query") streamerName : String,
        @Query("live_only") liveOnly : Boolean = false,
    ) : SearchApiDataResponse

    @GET("search/channels")
    suspend fun getPagingSearchData(
        @Header("Authorization") authorization : String,
        @Query("query") streamerName : String,
        @Query("first") size : Int,
        @Query("after") cursor : String?,
        @Query("live_only") liveOnly : Boolean = false,
    ) : SearchApiDataResponse

    @GET("streams")
    suspend fun getStreamData(
        @Header("Authorization") authorization : String,
        @Query("user_login") userLogin : String
    ) : StreamApiDataResponse

    @GET("streams")
    suspend fun getStreamDataList(
        @Header("Authorization") authorization : String,
        @Query("user_login") vararg userLogin : String
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
        @Header("Authorization") authorization: String,
        @Query("game_id") gameId: String,
        @Query("type") type: String = "live",
        @Query("language") vararg language: String = arrayOf("ko")
    ) : StreamApiDataResponse

    @GET("users")
    suspend fun getStreamerData(
        @Header("Authorization") authorization : String,
        @Query("login") vararg userLogin : String
    ) : StreamerApiDataResponse
}