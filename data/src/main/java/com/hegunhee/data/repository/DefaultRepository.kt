package com.hegunhee.data.repository

import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(private val dataSource: RemoteDataSource) : Repository {

    override suspend fun getStreamData(userLogin : String): Result<StreamDataType> {
        return runCatching {
            val token = dataSource.getAuthToken().getFormattedToken()
            val response = dataSource.getStreamDataResponse(userLogin = userLogin,token = token)
            if(response.streamApiData.isEmpty()){
                StreamDataType.EmptyData("","","")
            }else{
                response.streamApiData[0].toStreamData()
            }
        }
    }

    override suspend fun getJururuStreamData(jururuId: String): Result<StreamDataType> {
        return runCatching {
            val token = dataSource.getAuthToken().getFormattedToken()
            val response = dataSource.getStreamDataResponse(userLogin = jururuId,token = token)
            if(response.streamApiData.isEmpty()){
                StreamDataType.EmptyData(userLogin = "cotton__123",userName ="주르르",profileUrl = StreamDataType.TestJururuProfileUrl)
            }else{
                response.streamApiData[0].toStreamData()
            }
        }
    }
}