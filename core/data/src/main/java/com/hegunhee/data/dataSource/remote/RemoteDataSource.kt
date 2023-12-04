package com.hegunhee.data.dataSource.remote

import androidx.paging.PagingData
import com.hegunhee.data.data.json.twitch.TwitchAuthToken
import com.hegunhee.data.data.json.twitch.SearchApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamerApiDataResponse
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.model.twitch.SearchData
import kotlinx.coroutines.flow.Flow


interface RemoteDataSource {

    suspend fun getStreamDataResponse(streamerId : String) : StreamApiDataResponse

    suspend fun getStreamDataListResponse(vararg streamerId: String) : StreamApiDataResponse

    suspend fun getSearchDataResponse(streamerName : String) : SearchApiDataResponse

    suspend fun getStreamerDataResponse(vararg streamerId : String) : StreamerApiDataResponse

    suspend fun getGameStreamDataResponse(gameId : String) : StreamApiDataResponse

    suspend fun getSearchPagingDataResponse(streamerName : String,size : Int) : Flow<PagingData<SearchData>>

    suspend fun getKakaoSearchPagingData(query : String,sortType : KakaoSearchSortType,searchType : KakaoSearchType,size : Int) : Flow<PagingData<KakaoSearchData>>
}