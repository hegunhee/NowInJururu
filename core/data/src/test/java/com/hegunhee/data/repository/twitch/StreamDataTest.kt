package com.hegunhee.data.repository.twitch

import com.hegunhee.data.data.json.twitch.StreamApiData
import com.hegunhee.data.data.json.twitch.StreamApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamerApiData
import com.hegunhee.data.data.json.twitch.StreamerApiDataResponse
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.database.entity.StreamerEntity
import com.hegunhee.domain.model.twitch.StreamDataType
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
class StreamDataTest {

    @InjectMocks
    private lateinit var sut: DefaultTwitchStreamRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Test
    fun givenOnlineStreamId_whenGetStreamData_thenReturnOnlineStreamData() {
        runBlocking {
            // Given
            val streamerId = "cotton__123"
            whenever(remoteDataSource.getStreamerDataResponse(*arrayOf(streamerId))).thenReturn(
                streamerApiDataResponse(streamerId)
            )
            whenever(remoteDataSource.getStreamDataResponse(streamerId)).thenReturn(
                streamApiDataResponse(streamerId)
            )

            // When
            val streamData = sut.getStreamData(streamerId).getOrThrow()

            // Then
            streamData as StreamDataType.OnlineData
            assertEquals(streamData.streamerId, streamerId)

            verify(remoteDataSource).getStreamerDataResponse(*arrayOf(streamerId))
            verify(remoteDataSource).getStreamDataResponse(streamerId)
        }
    }

    @Test
    fun givenOfflineStreamId_whenGetStreamData_thenReturnOfflineStreamData() {
        runBlocking {
            // Given
            val streamerId = "cotton__123"
            whenever(remoteDataSource.getStreamerDataResponse(*arrayOf(streamerId))).thenReturn(
                streamerApiDataResponse(streamerId)
            )
            whenever(remoteDataSource.getStreamDataResponse(streamerId)).thenReturn(
                emptyStreamApiDataResponse(streamerId)
            )

            // When
            val streamData = sut.getStreamData(streamerId).getOrThrow()

            // Then
            streamData as StreamDataType.OfflineData

            assertEquals(streamData.streamerId, streamerId)

            verify(remoteDataSource).getStreamerDataResponse(*arrayOf(streamerId))
            verify(remoteDataSource).getStreamDataResponse(streamerId)
        }
    }

    @Test
    fun givenEmptyStreamerId_whenGetStreamDataList_thenReturnEmptyList() {
        runBlocking {
            // Given
            whenever(localDataSource.getAllStreamerList()).thenReturn(emptyList())

            // When
            val streamDataList = sut.getStreamDataList().getOrThrow()

            // Then
            assertTrue(streamDataList.isEmpty())

            verify(localDataSource).getAllStreamerList()
        }
    }

    @Test
    fun givenStreamEntityList_whenGetStreamDataList_thenReturnStreamDataList() {
        runBlocking {
            // Given
            val streamerId = "cotton__123"
            val streamerEntityList = listOf(StreamerEntity(streamerId, false))
            whenever(localDataSource.getAllStreamerList()).thenReturn(streamerEntityList)
            whenever(remoteDataSource.getStreamerDataResponse(streamerId = streamerEntityList.map { it.streamerLogin }
                .toTypedArray())).thenReturn(streamerApiDataResponse(streamerId))
            whenever(remoteDataSource.getStreamDataListResponse(streamerId = streamerEntityList.map { it.streamerLogin }
                .toTypedArray())).thenReturn(streamApiDataResponse(streamerId))

            // When
            val streamerDataList = sut.getStreamDataList().getOrThrow()

            // Then
            assertTrue(streamerDataList.isNotEmpty())
            assertTrue(streamerDataList.size == 1)

            verify(localDataSource).getAllStreamerList()
            verify(remoteDataSource).getStreamDataListResponse(streamerId)
            verify(remoteDataSource).getStreamDataListResponse(streamerId)
        }
    }

    private fun streamerApiDataResponse(streamerId: String): StreamerApiDataResponse {
        return StreamerApiDataResponse(listOf(streamerApiData(streamerId)))
    }

    private fun streamerApiData(streamerId: String): StreamerApiData {
        return StreamerApiData("", streamerId, "", "", "", "", "", "", 0, "")
    }

    private fun streamApiDataResponse(streamerId: String): StreamApiDataResponse {
        return StreamApiDataResponse(listOf(streamApiData(streamerId)), pagination = null)
    }

    private fun streamApiData(streamerId: String): StreamApiData {
        return StreamApiData(
            "",
            "",
            "",
            false,
            "",
            "",
            emptyList(),
            emptyList(),
            "",
            "",
            "",
            "",
            streamerId,
            "",
            0
        )
    }

    private fun emptyStreamApiDataResponse(streamerId: String): StreamApiDataResponse {
        return StreamApiDataResponse(listOf(), null)
    }
}
