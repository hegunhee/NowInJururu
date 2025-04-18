package com.hegunhee.domain.usecase.twitch

import com.hegunhee.domain.model.twitch.RecommendStreamData
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.repository.twitch.TwitchGameStreamRepository
import javax.inject.Inject

class GetGameStreamDataListUseCase @Inject constructor(private val gameStreamRepository: TwitchGameStreamRepository){

    suspend operator fun invoke(gameId : String) : Result<RecommendStreamData>{
        return gameStreamRepository.getGameStreamDataList(gameId)
    }
}