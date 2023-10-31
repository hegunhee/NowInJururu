package com.hegunhee.domain.usecase

import androidx.paging.PagingData
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchPagingDataUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(streamerName : String,size : Int) : Flow<PagingData<SearchData>> {
        return repository.searchPagingSource(streamerName,size)
    }
}