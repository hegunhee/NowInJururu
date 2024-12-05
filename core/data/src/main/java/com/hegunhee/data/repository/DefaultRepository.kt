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

    override suspend fun getStreamData(streamerId: String): Result<StreamDataType> {
        return runCatching {
            val streamerInfo = remoteDataSource
                .getStreamerDataResponse(streamerId = arrayOf(streamerId))
                .findByStreamerId(streamerId)

            val streamData = remoteDataSource.getStreamDataResponse(streamerId = streamerInfo.streamerId)

            if (streamData.isEmpty()) {
                streamerInfo.toOfflineData()
            } else {
                streamData.streamApiData[0].toStreamData(streamerInfo.profileImageUrl)
            }
        }
    }

    override suspend fun getStreamDataList(): Result<List<StreamDataType>> {
        return runCatching {
            val loadedStreamerLoginArray = localDataSource.getAllStreamerList().map { it.streamerLogin }.toTypedArray()

            if (loadedStreamerLoginArray.isEmpty()) {
                return@runCatching emptyList<StreamDataType>()
            }

            val streamerInfoList = remoteDataSource.getStreamerDataResponse(streamerId = loadedStreamerLoginArray).streamerApiDataList
            val streamInfoList =
                remoteDataSource.getStreamDataListResponse(streamerId = loadedStreamerLoginArray).streamApiData

            streamerInfoList.map { streamerInfo ->
                val streamDataOrNull = streamInfoList.find { it.streamerId == streamerInfo.streamerId }
                return@map streamDataOrNull?.toStreamData(streamerInfo.profileImageUrl) ?: streamerInfo.toOfflineData()
            }
        }
    }

    override suspend fun getGameStreamDataList(gameId: String): Result<List<StreamDataType.OnlineData>> {
        return runCatching {
            val gameStreamList = remoteDataSource.getGameStreamDataResponse(gameId).streamApiData.sortedBy { it.streamerId }

            if(gameStreamList.isEmpty()) {
                return@runCatching emptyList<StreamDataType.OnlineData>()
            }

            val streamerInfoList = remoteDataSource.getStreamerDataResponse(streamerId = gameStreamList.map { it.streamerId }.toTypedArray()).streamerApiDataList.sortedBy { it.streamerId }

            return@runCatching gameStreamList.zip(streamerInfoList).sortedByDescending { it.first.viewerCount }.map {
                it.first.toStreamData(it.second.profileImageUrl,RecommendStreamThumbNailWidth, RecommendStreamThumbNailHeight)
            }
        }
    }

    override suspend fun getSearchStreamerData(streamerId: String): Result<SearchData> {
        return runCatching {
            remoteDataSource.getSearchDataResponse(streamerName = streamerId).searchApiDataList.first { it.streamerId == streamerId}.toSearchData()
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
                val loadedStreamerData = localDataSource.getAllStreamerList().map { it.streamerLogin }
                it.filter { searchData ->
                    !loadedStreamerData.contains(searchData.streamerId)
                }
            }
    }

    override suspend fun getKakaoSearchPagingData(query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType, size: Int
    ): Flow<PagingData<KakaoSearchData>> {
        return remoteDataSource.getKakaoSearchPagingData(query,sortType,searchType,size)
    }
}