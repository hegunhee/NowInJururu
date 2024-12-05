package com.hegunhee.data.repository.twitch

import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toOfflineData
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.repository.twitch.TwitchStreamRepository
import javax.inject.Inject

class DefaultTwitchStreamRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : TwitchStreamRepository {

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

}
