package com.hegunhee.domain.usecase.twitch

import androidx.paging.PagingData
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.repository.twitch.TwitchPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchPagingDataUseCase @Inject constructor(private val twitchPagingRepository: TwitchPagingRepository) {

    suspend operator fun invoke(streamerName : String,size : Int) : Flow<PagingData<SearchData>> {
        return twitchPagingRepository.getSearchPagingSource(streamerName,size)
    }
}