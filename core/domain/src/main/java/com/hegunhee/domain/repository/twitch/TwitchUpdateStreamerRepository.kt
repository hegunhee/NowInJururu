package com.hegunhee.domain.repository.twitch

import com.hegunhee.domain.model.twitch.StreamerData

interface TwitchUpdateStreamerRepository {

    suspend fun insertStreamer(streamerData: StreamerData): Result<Unit>

    suspend fun deleteStreamer(streamerData: StreamerData): Result<Unit>

}
