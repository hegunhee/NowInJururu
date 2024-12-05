package com.hegunhee.data.repository.twitch

import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toSearchData
import com.hegunhee.data.mapper.toSearchDataList
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.repository.twitch.TwitchSearchStreamerRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DefaultTwitchSearchStreamerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : TwitchSearchStreamerRepository {

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

}
