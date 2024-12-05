package com.hegunhee.domain.repository.twitch

import com.hegunhee.domain.model.twitch.StreamDataType

interface TwitchStreamRepository {

    suspend fun getStreamData(streamerId: String): Result<StreamDataType>

    suspend fun getStreamDataList(): Result<List<StreamDataType>>

}
