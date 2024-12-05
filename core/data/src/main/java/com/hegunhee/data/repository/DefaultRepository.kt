package com.hegunhee.data.repository

import androidx.paging.PagingData
import androidx.paging.filter
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toOfflineData
import com.hegunhee.data.mapper.toSearchData
import com.hegunhee.data.mapper.toSearchDataList
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.data.mapper.toStreamerEntity
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.model.twitch.StreamDataType.Companion.RecommendStreamThumbNailHeight
import com.hegunhee.domain.model.twitch.StreamDataType.Companion.RecommendStreamThumbNailWidth
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource : LocalDataSource
) : Repository {

    override suspend fun getSearchStreamerData(streamerId: String): Result<SearchData> {
        return runCatching {
            remoteDataSource
                .getSearchDataResponse(streamerName = streamerId)
                .searchApiDataList.first { it.streamerId == streamerId}.toSearchData()
        }
    }


    override suspend fun getSearchStreamerDataList(streamerId: String): Result<List<SearchData>> = coroutineScope{
        runCatching {
            val response = async { remoteDataSource.getSearchDataResponse(streamerName = streamerId) }
            val loadedStreamerLoginData = localDataSource.getAllStreamerList().map { it.streamerLogin }

            response.await().searchApiDataList.toSearchDataList().filterNot { loadedStreamerLoginData.contains(it.streamerId)}
        }
    }

    override suspend fun insertStreamer(streamerData: StreamerData) : Result<Unit> {
        return runCatching {
            localDataSource.insertStreamer(streamerData.toStreamerEntity())
        }
    }

    override suspend fun deleteStreamer(streamerData: StreamerData): Result<Unit> {
        return kotlin.runCatching {
            localDataSource.deleteStreamer(streamerData.toStreamerEntity())
        }
    }

    override suspend fun searchPagingSource(streamerName: String,size : Int): Flow<PagingData<SearchData>> {
        return remoteDataSource
            .getSearchPagingDataResponse(streamerName, size)
            .map {
                val loadedStreamerData = localDataSource.getAllStreamerList().map { streamerInfo -> streamerInfo.streamerLogin }
                it.filter { searchData ->
                    !loadedStreamerData.contains(searchData.streamerId)
                }
            }
    }

    override suspend fun getKakaoSearchPagingData(
        query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType, size: Int
    ): Flow<PagingData<KakaoSearchData>> {
        return remoteDataSource.getKakaoSearchPagingData(query,sortType,searchType,size)
    }
}
