package com.hegunhee.data.repository

import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toSearchDataList
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.data.mapper.toStreamerEntity
import com.hegunhee.domain.model.SearchData
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.model.StreamerData
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource : LocalDataSource
) : Repository {

    override suspend fun getJururuStreamData(jururuId: String): Result<StreamDataType> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val jururuInfo = remoteDataSource.getStreamerDataResponse(streamerLogin = arrayOf<String>(jururuId),token = token).streamerApiDataList[0]
            val jururuStreamDataResponse = remoteDataSource.getStreamDataResponse(userLogin = jururuInfo.login,token = token)
            if(jururuStreamDataResponse.streamApiData.isEmpty()){
                StreamDataType.EmptyData(userLogin = jururuInfo.login,userName =jururuInfo.displayName,profileUrl = jururuInfo.profileImageUrl)
            }else{
                jururuStreamDataResponse.streamApiData[0].toStreamData(jururuInfo.profileImageUrl)
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
                val streamData = remoteDataSource.getStreamDataResponse(userLogin = streamerInfo.login,token = token).streamApiData
                return@map if(streamData.isNotEmpty()){
                    streamData[0].toStreamData(streamerInfo.profileImageUrl)
                }else{
                    StreamDataType.EmptyData(userLogin = streamerInfo.login,userName = streamerInfo.displayName,profileUrl = streamerInfo.profileImageUrl)
                }
            }
        }
    }

    override suspend fun getSearchStreamerDataList(streamerName: String): Result<List<SearchData>> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val response = remoteDataSource.getSearchDataResponse(streamerName = streamerName, token = token)
            val loadedStreamerLoginData = localDataSource.getAllStreamerList().map { it.streamerLogin }
            response.searchApiDataList.toSearchDataList().filterNot { loadedStreamerLoginData.contains(it.streamerLogin)}
        }
    }

    override suspend fun insertStreamer(streamerData: StreamerData) : Result<Unit> {
        return runCatching {
            localDataSource.insertStreamer(streamerData.toStreamerEntity())
        }
    }
}