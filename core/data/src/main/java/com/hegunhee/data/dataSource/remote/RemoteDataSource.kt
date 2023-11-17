package com.hegunhee.data.dataSource.remote

import androidx.paging.PagingData
import com.hegunhee.data.data.json.kakao.KakaoWebSearchResponse
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

    suspend fun getAuthToken() : TwitchAuthToken

    suspend fun getStreamDataResponse(userLogin : String,token : String) : StreamApiDataResponse

    suspend fun getStreamDataListResponse(vararg userLogin: String, token : String) : StreamApiDataResponse

    suspend fun getSearchDataResponse(streamerName : String,token : String) : SearchApiDataResponse

    suspend fun getStreamerDataResponse(vararg streamerLogin : String,token : String) : StreamerApiDataResponse

    suspend fun getGameStreamDataResponse(gameId : String,token : String) : StreamApiDataResponse

    suspend fun getSearchPagingDataResponse(streamerName : String,size : Int) : Flow<PagingData<SearchData>>

    suspend fun getKakaoSearchPagingData(query : String,sortType : KakaoSearchSortType,searchType : KakaoSearchType?,size : Int) : Flow<PagingData<KakaoSearchData>>
}