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

    override suspend fun getStreamData(userLogin : String): Result<StreamDataType> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val response = remoteDataSource.getStreamDataResponse(userLogin = userLogin,token = token)
            if(response.streamApiData.isEmpty()){
                StreamDataType.EmptyData("","","")
            }else{
                response.streamApiData[0].toStreamData()
            }
        }
    }

    override suspend fun getJururuStreamData(jururuId: String): Result<StreamDataType> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val response = remoteDataSource.getStreamDataResponse(userLogin = jururuId,token = token)
            if(response.streamApiData.isEmpty()){
                StreamDataType.EmptyData(userLogin = "cotton__123",userName ="주르르",profileUrl = StreamDataType.TestJururuProfileUrl)
            }else{
                response.streamApiData[0].toStreamData()
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

    override suspend fun insertStreamer(streamerData: StreamerData) {
        runCatching {
            localDataSource.insertStreamer(streamerData.toStreamerEntity())
        }
    }
}