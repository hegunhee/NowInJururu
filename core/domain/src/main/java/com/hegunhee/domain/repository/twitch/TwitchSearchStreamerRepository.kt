package com.hegunhee.domain.repository.twitch

import com.hegunhee.domain.model.twitch.SearchData

interface TwitchSearchStreamerRepository {

    suspend fun getSearchStreamerData(streamerId: String): Result<SearchData>

    suspend fun getSearchStreamerDataList(streamerId: String): Result<List<SearchData>>

}
