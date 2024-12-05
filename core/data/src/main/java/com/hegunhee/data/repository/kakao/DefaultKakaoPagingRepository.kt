package com.hegunhee.data.repository.kakao

import androidx.paging.PagingData
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.repository.kakao.KakaoPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultKakaoPagingRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : KakaoPagingRepository {

    override suspend fun getSearchPagingData(
        query: String, sortType: KakaoSearchSortType, searchType: KakaoSearchType, size: Int
    ): Flow<PagingData<KakaoSearchData>> {
        return remoteDataSource.getKakaoSearchPagingData(query, sortType, searchType, size)
    }
}
