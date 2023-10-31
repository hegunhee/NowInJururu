package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject


class InsertStreamerDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(streamerData: StreamerData) : Result<Unit> {
        return repository.insertStreamer(streamerData)
    }
}