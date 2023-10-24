package com.hegunhee.data.dataSource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hegunhee.data.data.json.twitch.TwitchAuthToken
import com.hegunhee.data.data.json.twitch.SearchApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamerApiDataResponse
import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchService
import com.hegunhee.domain.model.SearchData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val twitchAuthService : TwitchAuthService,
    private val twitchService : TwitchService
) : RemoteDataSource {
    override suspend fun getAuthToken(): TwitchAuthToken {
        return twitchAuthService.getAuthToken()
    }

    override suspend fun getStreamDataResponse(userLogin : String, token: String): StreamApiDataResponse {
        return twitchService.getStreamData(userLogin = userLogin,authorization = token)
    }

    override suspend fun getSearchDataResponse(
        streamerName: String,
        token: String
    ): SearchApiDataResponse {
        return twitchService.getSearchData(streamerName = streamerName, authorization = token)
    }

    override suspend fun getStreamerDataResponse(
        vararg streamerLogin: String,
        token: String
    ): StreamerApiDataResponse {
        return twitchService.getStreamerData(userLogin = streamerLogin, authorization = token)
    }

    override suspend fun getGameStreamDataResponse(gameId: String,token : String): StreamApiDataResponse {
        return twitchService.getGameStreamData(gameId = gameId, authorization = token)
    }

    override suspend fun getSearchPagingDataResponse(
        streamerName: String,
        size: Int,
    ): Flow<PagingData<SearchData>> {
        return Pager(
            config = PagingConfig(pageSize = size, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(query = streamerName,twitchAuthService,twitchService)}
        ).flow
    }

}