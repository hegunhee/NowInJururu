package com.hegunhee.domain.usecase.twitch

import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.repository.Repository
import com.hegunhee.domain.repository.twitch.TwitchUpdateStreamerRepository
import javax.inject.Inject


class InsertStreamerDataUseCase @Inject constructor(private val twitchUpdateStreamerRepository: TwitchUpdateStreamerRepository) {

    suspend operator fun invoke(streamerData: StreamerData) : Result<Unit> {
        return twitchUpdateStreamerRepository.insertStreamer(streamerData)
    }
}