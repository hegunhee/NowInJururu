package com.hegunhee.domain.repository.twitch

import com.hegunhee.domain.model.twitch.StreamDataType

interface TwitchGameStreamRepository {

    suspend fun getGameStreamDataList(gameId: String): Result<List<StreamDataType.OnlineData>>
}