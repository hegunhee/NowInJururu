package com.hegunhee.data.repository.twitch

import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.domain.model.platform.TwitchGame
import com.hegunhee.domain.model.twitch.RecommendStreamData
import com.hegunhee.domain.model.twitch.StreamDataType.Companion.RecommendStreamThumbNailHeight
import com.hegunhee.domain.model.twitch.StreamDataType.Companion.RecommendStreamThumbNailWidth
import com.hegunhee.domain.repository.twitch.TwitchGameStreamRepository
import javax.inject.Inject

class DefaultTwitchGameStreamRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : TwitchGameStreamRepository{

    override suspend fun getGameStreamDataList(gameId: String): Result<RecommendStreamData> {
        return runCatching {
            val gameStreamList = remoteDataSource.getGameStreamDataResponse(gameId).streamApiData.sortedBy { it.streamerId }

            if(gameStreamList.isEmpty()) {
                return@runCatching RecommendStreamData(gameId,TwitchGame(gameId), emptyList())
            }

            val streamerInfoList = remoteDataSource
                .getStreamerDataResponse(streamerId = gameStreamList.map { it.streamerId }.toTypedArray())
                .streamerApiDataList.sortedBy { it.streamerId }

            val gameOnlineStreamList = gameStreamList.zip(streamerInfoList).sortedByDescending { it.first.viewerCount }.map {
                it.first.toStreamData(it.second.profileImageUrl,
                    RecommendStreamThumbNailWidth, RecommendStreamThumbNailHeight
                )
            }

            RecommendStreamData(gameId,TwitchGame(gameId),gameOnlineStreamList)
        }
    }

}
