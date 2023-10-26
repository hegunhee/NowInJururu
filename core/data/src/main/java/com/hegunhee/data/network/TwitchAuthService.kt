package com.hegunhee.data.network

import com.hegunhee.data.BuildConfig
import com.hegunhee.data.data.json.twitch.TwitchAuthToken
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitchAuthService {

    @POST("oauth2/token")
    suspend fun getAuthToken(
        @Query("client_id") clientId : String = BuildConfig.TwitchClientId,
        @Query("client_secret") clientSecret : String = BuildConfig.TwitchClientSecret,
        @Query("grant_type") grantType : String = "client_credentials"
    ) : TwitchAuthToken

}