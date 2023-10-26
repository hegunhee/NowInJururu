package com.hegunhee.data.network

import com.hegunhee.data.BuildConfig
import com.hegunhee.data.data.json.twitch.TwitchAuthToken
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitchAuthService {

    @POST("oauth2/token")
    suspend fun getAuthToken(
        @Query("client_id") clientId : String = BuildConfig.clientId,
        @Query("client_secret") clientSecret : String = BuildConfig.clientSecret,
        @Query("grant_type") grantType : String = "client_credentials"
    ) : TwitchAuthToken

}