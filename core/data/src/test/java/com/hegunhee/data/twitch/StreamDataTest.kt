package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class StreamDataTest {

    private lateinit var sut : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        sut = getTwitchGetRetrofit(getMoshi()).getTwitchService()
    }

    // 현재 주르르는 트위치 방송을 중단한 상태, 그러므로 앞으로 값이 조회될 일 없음
    @Test
    fun givenStreamerId_whenGetStreamData_thenReturnEmptyStreamData() = runBlocking {
        // Given
        val streamerId = "cotton__123"

        // When
        val streamData = sut.getStreamData(streamerId = streamerId).streamApiData
        println(streamData)

        // Then
        Assert.assertTrue(streamData.isEmpty())
    }

    @Test
    fun givenBlankStreamerId_whenGetStreamData_thenThrowHttpException(): Unit = runBlocking {
        // Given

        // When & Then
        Assert.assertThrows(HttpException::class.java) { runBlocking { sut.getStreamData(streamerId = "") } }
    }

}
