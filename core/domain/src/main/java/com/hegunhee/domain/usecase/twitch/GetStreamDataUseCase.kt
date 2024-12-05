package com.hegunhee.domain.usecase.twitch

import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.repository.twitch.TwitchStreamRepository
import javax.inject.Inject

class GetStreamDataUseCase @Inject constructor(private val twitchStreamRepository: TwitchStreamRepository) {

    suspend operator fun invoke(streamerId: String): Result<StreamDataType> {
        return twitchStreamRepository.getStreamData(streamerId)
    }
}