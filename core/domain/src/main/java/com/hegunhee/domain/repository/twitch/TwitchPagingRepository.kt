package com.hegunhee.domain.repository.twitch

import androidx.paging.PagingData
import com.hegunhee.domain.model.twitch.SearchData
import kotlinx.coroutines.flow.Flow

interface TwitchPagingRepository {

    suspend fun getSearchPagingSource(streamerName : String,size : Int) : Flow<PagingData<SearchData>>

}
