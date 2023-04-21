package com.hegunhee.data.dataSource.remote

import com.hegunhee.data.data.json.AuthToken
import com.hegunhee.data.data.json.StreamApiDataResponse
import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchStreamDataApi
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(private val twitchAuthTokenApi : TwitchAuthTokenApi, private val twitchStreamDataApi: TwitchStreamDataApi)
    : RemoteDataSource {
    override suspend fun getAuthToken(): AuthToken {
        return twitchAuthTokenApi.getAuthToken()
    }

    override suspend fun getStreamDataResponse(userId : String,token: String): StreamApiDataResponse {
        return twitchStreamDataApi.getStreamData(Authorization = token)
    }


}