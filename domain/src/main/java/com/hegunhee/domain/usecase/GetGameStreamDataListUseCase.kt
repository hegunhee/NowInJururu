package com.hegunhee.domain.usecase

import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.repository.Repository
import javax.inject.Inject

class GetGameStreamDataListUseCase @Inject constructor(private val repository : Repository){

    suspend operator fun invoke(gameId : String) : Result<List<StreamDataType.OnlineData>>{
        return repository.getGameStreamDataList(gameId)
    }
}