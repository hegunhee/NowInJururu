package com.hegunhee.data.dataSource.remote

import com.hegunhee.data.data.json.AuthToken
import com.hegunhee.data.data.json.SearchApiDataResponse
import com.hegunhee.data.data.json.StreamApiDataResponse
import com.hegunhee.data.data.json.StreamerApiDataResponse
import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchSearchDataApi
import com.hegunhee.data.network.TwitchStreamDataApi
import com.hegunhee.data.network.TwitchStreamerDataApi
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val twitchAuthTokenApi : TwitchAuthTokenApi,
    private val twitchStreamDataApi: TwitchStreamDataApi,
    private val twitchSearchDataApi : TwitchSearchDataApi,
    private val twitchStreamerDataApi: TwitchStreamerDataApi
    )
    : RemoteDataSource {
    override suspend fun getAuthToken(): AuthToken {
        return twitchAuthTokenApi.getAuthToken()
    }

    override suspend fun getStreamDataResponse(userLogin : String, token: String): StreamApiDataResponse {
        return twitchStreamDataApi.getStreamData(userLogin = userLogin,authorization = token)
    }

    override suspend fun getSearchDataResponse(
        streamerName: String,
        token: String
    ): SearchApiDataResponse {
        return twitchSearchDataApi.getSearchData(streamerName = streamerName, authorization = token)
    }

    override suspend fun getStreamerDataResponse(
        vararg streamerLogin: String,
        token: String
    ): StreamerApiDataResponse {
        return twitchStreamerDataApi.getStreamerData(userLogin = streamerLogin, authorization = token)
    }

    override suspend fun getGameStreamDataResponse(gameId: String,token : String): StreamApiDataResponse {
        return twitchStreamDataApi.getGameStreamData(gameId = gameId, authorization = token)
    }


}