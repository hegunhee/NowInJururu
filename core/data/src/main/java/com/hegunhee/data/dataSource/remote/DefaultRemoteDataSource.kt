package com.hegunhee.data.dataSource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hegunhee.data.data.json.twitch.SearchApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamerApiDataResponse
import com.hegunhee.data.network.KakaoService
import com.hegunhee.data.network.TwitchService
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.model.twitch.SearchData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val twitchService : TwitchService,
    private val kakaoService : KakaoService
) : RemoteDataSource {

    override suspend fun getStreamDataResponse(streamerId : String): StreamApiDataResponse {
        return twitchService.getStreamData(streamerId = streamerId)
    }

    override suspend fun getStreamDataListResponse(
        vararg streamerId: String
    ): StreamApiDataResponse {
        return twitchService.getStreamDataList(streamerId = streamerId)
    }

    override suspend fun getSearchDataResponse(
        streamerName: String
    ): SearchApiDataResponse {
        return twitchService.getSearchData(streamerName = streamerName)
    }

    override suspend fun getStreamerDataResponse(
        vararg streamerId: String
    ): StreamerApiDataResponse {
        return twitchService.getStreamerData(streamerId = streamerId)
    }

    override suspend fun getGameStreamDataResponse(gameId: String): StreamApiDataResponse {
        return twitchService.getGameStreamData(gameId = gameId)
    }

    override suspend fun getSearchPagingDataResponse(
        streamerName: String,
        size: Int,
    ): Flow<PagingData<SearchData>> {
        return Pager(
            config = PagingConfig(pageSize = size, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(query = streamerName,twitchService)}
        ).flow
    }

    override suspend fun getKakaoSearchPagingData(query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType, size: Int) : Flow<PagingData<KakaoSearchData>> {
        return Pager(
            config = PagingConfig(
                size,
                enablePlaceholders = false,
                initialLoadSize = size
            ),
            pagingSourceFactory = { KakaoSearchPagingSource(query = query,kakaoService,sortType.sort,searchType)}
        ).flow
    }
}