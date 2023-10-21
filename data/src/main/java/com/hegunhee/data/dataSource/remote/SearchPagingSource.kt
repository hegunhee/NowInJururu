package com.hegunhee.data.dataSource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hegunhee.data.mapper.toSearchData
import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchSearchDataApi
import com.hegunhee.domain.model.SearchData

class SearchPagingSource(
    private val query: String,
    private val twitchApiTokenApi: TwitchAuthTokenApi,
    private val twitchSearchDataApi: TwitchSearchDataApi,
) : PagingSource<String, SearchData>() {

    override val keyReuseSupported: Boolean
        get() = true

    override suspend fun load(params: LoadParams<String>): LoadResult<String, SearchData> {
        return try {
            val authToken = twitchApiTokenApi.getAuthToken().getFormattedToken()
            val searchResponse = twitchSearchDataApi.getPagingSearchData(
                streamerName = query,
                authorization = authToken,
                size = params.loadSize,
                cursor = params.key,
            )
            val nextKey = searchResponse.pagination?.cursor
            LoadResult.Page(
                data = searchResponse.searchApiDataList.map { it.toSearchData() },
                prevKey = params.key,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, SearchData>): String? {
        return state.pages[0].prevKey
    }
}