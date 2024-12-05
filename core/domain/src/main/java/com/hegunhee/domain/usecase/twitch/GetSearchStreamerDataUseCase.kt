package com.hegunhee.domain.usecase.twitch

import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.repository.twitch.TwitchSearchStreamerRepository
import javax.inject.Inject

class GetSearchStreamerDataUseCase @Inject constructor(private val twitchSearchStreamerRepository: TwitchSearchStreamerRepository) {

    suspend operator fun invoke(streamerId : String) : Result<SearchData> {
        return twitchSearchStreamerRepository.getSearchStreamerData(streamerId)
    }
}