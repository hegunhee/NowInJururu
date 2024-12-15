package com.hegunhee.domain.repository.twitch

import com.hegunhee.domain.model.twitch.RecommendStreamData

interface TwitchGameStreamRepository {

    suspend fun getGameStreamDataList(gameId: String): Result<RecommendStreamData>
}