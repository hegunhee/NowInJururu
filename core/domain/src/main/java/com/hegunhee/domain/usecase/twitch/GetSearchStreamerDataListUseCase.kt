package com.hegunhee.domain.usecase.twitch

import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.repository.Repository
import com.hegunhee.domain.repository.twitch.TwitchSearchStreamerRepository
import javax.inject.Inject

class GetSearchStreamerDataListUseCase @Inject constructor(private val twitchSearchStreamerRepository: TwitchSearchStreamerRepository) {

    suspend operator fun invoke(streamerName : String) : Result<List<SearchData>> {
        return twitchSearchStreamerRepository.getSearchStreamerDataList(streamerName)
    }
}