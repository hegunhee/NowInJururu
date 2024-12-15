package com.hegunhee.data.repository.twitch

import androidx.paging.PagingData
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.domain.model.platform.TwitchStreamer
import com.hegunhee.domain.model.twitch.SearchData
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
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
    private lateinit var sut: DefaultTwitchPagingRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Test
    fun givenStreamerIdAndPageSize_whenSearchData_thenReturnPagingData() {
        runBlocking {
            // Given
            val streamerName = "주르르"
            val size = 20
            whenever(remoteDataSource.getSearchPagingDataResponse(streamerName, size)).thenReturn(
                flowOf(createPagingData(streamerName))
            )
            whenever(localDataSource.getAllStreamerList()).thenReturn(emptyList())

            // When
            val pagingData = sut.getSearchPagingSource(streamerName, size)

            // Then
            Assert.assertTrue(pagingData.count() == 1)

            verify(remoteDataSource).getSearchPagingDataResponse(streamerName, size)
            verify(localDataSource).getAllStreamerList()
        }
    }

    private fun createPagingData(streamerName: String): PagingData<SearchData> {
        return PagingData.from(listOf(createSearchData(streamerName)))
    }

    private fun createSearchData(streamerName: String): SearchData {
        return SearchData(TwitchStreamer(streamerName), streamerName, "", "",false, emptyList(), "", "")
    }
}
