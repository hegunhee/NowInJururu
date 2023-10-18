package com.hegunhee.data.dataSource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hegunhee.data.data.json.AuthToken
import com.hegunhee.data.data.json.SearchApiDataResponse
import com.hegunhee.data.data.json.StreamApiDataResponse
import com.hegunhee.data.data.json.StreamerApiDataResponse
import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchSearchDataApi
import com.hegunhee.data.network.TwitchStreamDataApi
import com.hegunhee.data.network.TwitchStreamerDataApi
import com.hegunhee.domain.model.SearchData
import kotlinx.coroutines.flow.Flow
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

    override suspend fun getSearchPagingDataResponse(
        streamerName: String,
        size: Int,
    ): Flow<PagingData<SearchData>> {
        return Pager(
            config = PagingConfig(pageSize = size, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(query = streamerName,twitchAuthTokenApi,twitchSearchDataApi)}
        ).flow
    }

}