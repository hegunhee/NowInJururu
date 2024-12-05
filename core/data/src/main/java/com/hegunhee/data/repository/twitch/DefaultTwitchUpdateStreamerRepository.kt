package com.hegunhee.data.repository.twitch

import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.mapper.toStreamerEntity
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.repository.twitch.TwitchUpdateStreamerRepository
import javax.inject.Inject

class DefaultTwitchUpdateStreamerRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) : TwitchUpdateStreamerRepository {

    override suspend fun insertStreamer(streamerData: StreamerData) : Result<Unit> {
        return runCatching {
            localDataSource.insertStreamer(streamerData.toStreamerEntity())
        }
    }

    override suspend fun deleteStreamer(streamerData: StreamerData): Result<Unit> {
        return runCatching {
            localDataSource.deleteStreamer(streamerData.toStreamerEntity())
        }
    }

}
