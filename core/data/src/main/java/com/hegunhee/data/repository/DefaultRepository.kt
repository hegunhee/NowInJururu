package com.hegunhee.data.repository

import androidx.paging.PagingData
import androidx.paging.filter
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toModel
import com.hegunhee.data.mapper.toOfflineData
import com.hegunhee.data.mapper.toSearchDataList
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.data.mapper.toStreamerEntity
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.model.twitch.StreamDataType.Companion.RecommendStreamThumbNailHeight
import com.hegunhee.domain.model.twitch.StreamDataType.Companion.RecommendStreamThumbNailWidth
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource : LocalDataSource
) : Repository {

    override suspend fun getStreamData(streamerId: String): Result<StreamDataType> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val streamerInfo = remoteDataSource.getStreamerDataResponse(streamerLogin = arrayOf(streamerId),token = token).streamerApiDataList[0]
            val streamData = remoteDataSource.getStreamDataResponse(userLogin = streamerInfo.streamerId,token = token)
            if(streamData.streamApiData.isEmpty()){
                streamerInfo.toOfflineData()
            }else{
                streamData.streamApiData[0].toStreamData(streamerInfo.profileImageUrl)
            }
        }
    }

    override suspend fun getStreamDataList(): Result<List<StreamDataType>> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val loadedStreamerLoginArray = localDataSource.getAllStreamerList().map { it.streamerLogin }.toTypedArray()
            if(loadedStreamerLoginArray.isEmpty()){
                return@runCatching emptyList<StreamDataType>()
            }
            val streamerInfoList = remoteDataSource.getStreamerDataResponse(streamerLogin = loadedStreamerLoginArray,token = token).streamerApiDataList
            streamerInfoList.map{ streamerInfo ->
                val streamData = remoteDataSource.getStreamDataResponse(userLogin = streamerInfo.streamerId,token = token).streamApiData
                return@map if(streamData.isNotEmpty()){
                    streamData[0].toStreamData(streamerInfo.profileImageUrl)
                }else{
                    streamerInfo.toOfflineData()
                }
            }
        }
    }

    override suspend fun getGameStreamDataList(gameId: String): Result<List<StreamDataType.OnlineData>> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val gameStreamList = remoteDataSource.getGameStreamDataResponse(gameId,token).streamApiData.sortedBy { it.streamerId }
            if(gameStreamList.isEmpty()) {
                return@runCatching emptyList<StreamDataType.OnlineData>()
            }
            val streamerInfoList = remoteDataSource.getStreamerDataResponse(streamerLogin = gameStreamList.map { it.streamerId }.toTypedArray(),token = token).streamerApiDataList.sortedBy { it.streamerId }
            return@runCatching gameStreamList.zip(streamerInfoList).sortedByDescending { it.first.viewerCount }.map {
                it.first.toStreamData(it.second.profileImageUrl,RecommendStreamThumbNailWidth, RecommendStreamThumbNailHeight)
            }
        }
    }


    override suspend fun getSearchStreamerDataList(streamerName: String): Result<List<SearchData>> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val response = remoteDataSource.getSearchDataResponse(streamerName = streamerName, token = token)
            val loadedStreamerLoginData = localDataSource.getAllStreamerList().map { it.streamerLogin }
            response.searchApiDataList.toSearchDataList().filterNot { loadedStreamerLoginData.contains(it.streamerId)}
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

    override suspend fun getWebSearchDataList(
        query: String,
        sort: KakaoSearchSortType
    ): Result<List<KakaoSearchData.Web>> {
        return runCatching {
            remoteDataSource.getKakaoWebSearchResponse(query,sort.name).kakaoWebSearchData.map { it.toModel() }
        }
    }
}