package com.hegunhee.data.repository

import androidx.paging.PagingData
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override suspend fun getKakaoSearchPagingData(
        query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType, size: Int
    ): Flow<PagingData<KakaoSearchData>> {
        return remoteDataSource.getKakaoSearchPagingData(query,sortType,searchType,size)
    }
}
