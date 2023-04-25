package com.hegunhee.data.repository

import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(private val dataSource: RemoteDataSource) : Repository {

    override suspend fun getStreamData(userId : String): Result<StreamDataType> {
        return runCatching {
            val token = dataSource.getAuthToken().getFormattedToken()
            val response = dataSource.getStreamDataResponse(userId = userId,token = token)
            if(response.streamApiData.isEmpty()){
                StreamDataType.EmptyData("","","")
            }else{
                response.streamApiData[0].toStreamData()
            }
        }
    }
}