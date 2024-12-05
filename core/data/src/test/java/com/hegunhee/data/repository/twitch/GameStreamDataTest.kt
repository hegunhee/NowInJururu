package com.hegunhee.data.repository.twitch

import com.hegunhee.data.data.json.twitch.StreamApiData
import com.hegunhee.data.data.json.twitch.StreamApiDataResponse
import com.hegunhee.data.data.json.twitch.StreamerApiData
import com.hegunhee.data.data.json.twitch.StreamerApiDataResponse
import com.hegunhee.data.dataSource.local.LocalDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import com.hegunhee.data.repository.DefaultRepository
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
class GameStreamDataTest {

    @InjectMocks
    private lateinit var sut: DefaultRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Test
    fun givenEmptyGameId_whenGetStreamData_thenReturnEmptyList() {
        runBlocking {
            // Given
            val gameId = ""
            whenever(remoteDataSource.getGameStreamDataResponse(gameId)).thenReturn(
                StreamApiDataResponse(listOf(), null)
            )

            // When
            val gameStreamDataList = sut.getGameStreamDataList(gameId).getOrThrow()

            // Then
            assertTrue(gameStreamDataList.isEmpty())

            verify(remoteDataSource).getGameStreamDataResponse(gameId)
        }
    }

    @Test
    fun givenGameId_whenGetStreamData_thenReturnStreamData() {
        runBlocking {
            // Given
            val gameId = "17767"
            whenever(remoteDataSource.getGameStreamDataResponse(gameId)).thenReturn(
                createGameStreamDataResponse(gameId)
            )
            whenever(remoteDataSource.getStreamerDataResponse("")).thenReturn(
                createStreamerApiDataResponse(streamerId = "")
            )

            // When
            val gameStreamDataList = sut.getGameStreamDataList(gameId).getOrThrow()

            // Then
            assertTrue(gameStreamDataList.size == 1)
            assertTrue(gameStreamDataList[0].gameId == gameId)

            verify(remoteDataSource).getGameStreamDataResponse(gameId)
            verify(remoteDataSource).getStreamerDataResponse("")
        }
    }

    private fun createGameStreamDataResponse(gameId: String): StreamApiDataResponse {
        return StreamApiDataResponse(
            listOf(
                StreamApiData(
                    gameId, "", "", false, "", "",
                    emptyList(),
                    emptyList(), "", "", "", "", "", "", 0
                )
            ), null
        )
    }

    private fun createStreamerApiDataResponse(streamerId: String): StreamerApiDataResponse {
        return StreamerApiDataResponse(
            listOf(
                StreamerApiData(
                    "",
                    streamerId,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0,
                    ""
                )
            )
        )
    }

}
