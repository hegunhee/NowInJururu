package com.hegunhee.data.repository.twitch

import com.hegunhee.data.data.json.twitch.SearchApiData
import com.hegunhee.data.data.json.twitch.SearchApiDataResponse
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.repository.DefaultRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchDataTest {

    @InjectMocks
    private lateinit var sut: DefaultRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Test
    fun givenStreamerId_whenGetStreamData_thenReturnStreamData() {
        runBlocking {
            // Given
            val streamerId = "cotton__123"
            whenever(remoteDataSource.getSearchDataResponse(streamerId)).thenReturn(
                createSearchApiDataResponse(streamerId)
            )

            // When
            val searchData = sut.getSearchStreamerData(streamerId).getOrThrow()

            // Then
            assertEquals(searchData.streamerId, streamerId)

            verify(remoteDataSource).getSearchDataResponse(streamerId)
        }
    }

    @Test
    fun givenStreamerId_whenGetStreamData_thenReturnStreamDataList() {
        runBlocking {
            // Given
            val streamerId = "cotton__123"
            whenever(remoteDataSource.getSearchDataResponse(streamerId)).thenReturn(
                createSearchApiDataResponse(streamerId)
            )
            whenever(localDataSource.getAllStreamerList()).thenReturn(listOf())

            // When
            val searchDataList = sut.getSearchStreamerDataList(streamerId).getOrThrow()

            // Then
            assertTrue(searchDataList.size == 1)

            verify(remoteDataSource).getSearchDataResponse(streamerId)
            verify(localDataSource).getAllStreamerList()
        }
    }

    private fun createSearchApiDataResponse(streamerId: String): SearchApiDataResponse {
        return SearchApiDataResponse(
            listOf(
                SearchApiData(
                    "",
                    streamerId,
                    "",
                    "",
                    "",
                    "",
                    false,
                    "",
                    emptyList(),
                    emptyList(),
                    "",
                    ""
                ),
            ),
            null
        )
    }
}
