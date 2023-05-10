package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.StreamerData
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject


class InsertStreamerDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(streamerData: StreamerData) {
        repository.insertStreamer(streamerData)
    }
}