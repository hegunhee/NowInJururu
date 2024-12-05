package com.hegunhee.data.repository.twitch

import androidx.paging.PagingData
import androidx.paging.filter
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.repository.twitch.TwitchPagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTwitchPagingRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : TwitchPagingRepository {

    override suspend fun getSearchPagingSource(
        streamerName: String,
        size: Int
    ): Flow<PagingData<SearchData>> {
        return remoteDataSource
            .getSearchPagingDataResponse(streamerName, size)
            .map {
                val loadedStreamerData = localDataSource.getAllStreamerList()
                    .map { streamerInfo -> streamerInfo.streamerLogin }
                it.filter { searchData ->
                    !loadedStreamerData.contains(searchData.streamerId)
                }
            }
    }

}
