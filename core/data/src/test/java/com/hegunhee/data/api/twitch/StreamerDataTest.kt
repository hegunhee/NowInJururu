package com.hegunhee.data.api.twitch

import com.hegunhee.data.di.NetworkModule.provideTwitchAuthMoshi
import com.hegunhee.data.di.NetworkModule.provideTwitchAuthService
import com.hegunhee.data.di.NetworkModule.provideTwitchService
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class StreamerDataTest {

    private lateinit var sut: TwitchService

    @Before
    fun initMoshiAndApi() {
        sut =  provideTwitchService(
            moshi = provideTwitchAuthMoshi(),
            twitchAuthService = provideTwitchAuthService(provideTwitchAuthMoshi()),
        )
    }

    @Test
    fun givenStreamerId_whenGetStreamerDataList_thenStreamerDataListContainStreamerId() = runBlocking {
        // Given
        val streamerId = "cotton__123"
        val streamerIds = listOf(streamerId)

        // When
        val streamerDataList = sut.getStreamerData(streamerId = streamerIds.toTypedArray()).streamerApiDataList

        // Then
        Assert.assertTrue(streamerDataList.map { it.streamerId }.contains(streamerId))
    }

    @Test
    fun givenStreamerIds_whenGetStreamerDataList_thenReturnStreamerIdsEqualsStreamerId() = runBlocking {
        // Given
        val streamerIds = listOf("cotton__123","viichan6")

        // When
        val findStreamerIds = sut.getStreamerData(streamerId = streamerIds.toTypedArray()).streamerApiDataList.map { it.streamerId }

        // Then
        assertEquals(findStreamerIds,streamerIds)
    }

    @Test
    fun givenEmptyStreamerIds_whenGetStreamerDataList_thenThrowHttpException(): Unit = runBlocking {
        // Given
        val streamerIds = emptyList<String>()

        // When & Then
        Assert.assertThrows(HttpException::class.java) { runBlocking { sut.getStreamerData(streamerId = streamerIds.toTypedArray()) } }
    }

}
