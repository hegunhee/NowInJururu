package com.hegunhee.domain.usecase.twitch

import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetGameStreamDataListUseCase @Inject constructor(private val repository : Repository){

    suspend operator fun invoke(gameId : String) : Result<List<StreamDataType.OnlineData>>{
        return repository.getGameStreamDataList(gameId)
    }
}