package com.hegunhee.data.repository.twitch

import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.mapper.toStreamerEntity
import com.hegunhee.domain.model.twitch.StreamerData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class InsertDeleteDataTest {

    @InjectMocks
    private lateinit var sut: DefaultTwitchUpdateStreamerRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Test
    fun given_whenInsertStreamerData_thenInsertStreamerData() {
        runBlocking {
            // Given
            val streamerData = StreamerData("cotton__123", false)
            whenever(localDataSource.insertStreamer(streamerData.toStreamerEntity())).thenReturn(
                Unit
            )

            // When
            val insertResponse = sut.insertStreamer(streamerData).getOrThrow()

            // Then
            assertEquals(insertResponse, Unit)

            verify(localDataSource).insertStreamer(streamerData.toStreamerEntity())
        }
    }

    @Test
    fun given_whenDeleteStreamerData_thenDeleteStreamerData() {
        runBlocking {
            // Given
            val streamerData = StreamerData("cotton__123", false)
            whenever(localDataSource.deleteStreamer(streamerData.toStreamerEntity())).thenReturn(
                Unit
            )

            // When
            val deleteResponse = sut.deleteStreamer(streamerData).getOrThrow()

            // Then
            assertEquals(deleteResponse, Unit)

            verify(localDataSource).deleteStreamer(streamerData.toStreamerEntity())
        }
    }
}
