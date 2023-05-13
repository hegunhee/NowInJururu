package com.hegunhee.data.Test

import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.domain.Test.TestRepository
import com.hegunhee.domain.model.StreamDataType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestDefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : TestRepository {

    override suspend fun getStreamData(userLogin : String): Result<StreamDataType> {
        return runCatching {
            val token = remoteDataSource.getAuthToken().getFormattedToken()
            val response = remoteDataSource.getStreamDataResponse(userLogin = userLogin,token = token)
            if(response.streamApiData.isEmpty()){
                StreamDataType.EmptyData("","","")
            }else{
                response.streamApiData[0].toStreamData("")
            }
        }
    }
}