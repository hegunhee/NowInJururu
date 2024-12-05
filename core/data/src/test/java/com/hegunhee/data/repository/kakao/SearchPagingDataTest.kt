package com.hegunhee.data.repository.kakao

import androidx.paging.PagingData
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchPagingDataTest {

    @InjectMocks
    private lateinit var sut: DefaultKakaoPagingRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Test
    fun givenPagerInfo_whenSearchPagingData_thenReturnPagingData() {
        runBlocking {
            // Given
            val query = "주르르"
            val sortType = KakaoSearchSortType.Accuracy
            val searchType = KakaoSearchType.Web
            val size = 20
            whenever(
                remoteDataSource.getKakaoSearchPagingData(
                    query,
                    sortType,
                    searchType,
                    size
                )
            ).thenReturn(flowOf(createPagingData(query)))

            // When
            val pagingData = sut.getSearchPagingData(query, sortType, searchType, size)

            // Then
            assertTrue(pagingData.count() == 1)

            verify(remoteDataSource).getKakaoSearchPagingData(query, sortType, searchType, size)
        }
    }

    private fun createPagingData(streamerName: String): PagingData<KakaoSearchData> {
        return PagingData.from(listOf(createSearchWebData(streamerName)))
    }

    private fun createSearchWebData(streamerName: String): KakaoSearchData {
        return KakaoSearchData.Web("", "", streamerName, "")
    }

}
