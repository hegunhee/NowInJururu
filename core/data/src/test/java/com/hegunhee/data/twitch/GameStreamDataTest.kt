package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameStreamDataTest {

    private lateinit var sut: TwitchService

    @Before
    fun initMoshiAndRetrofit() {
        sut = getTwitchGetRetrofit(getMoshi()).getTwitchService()
    }

    /**
     * mapleStory = "19976"
     */
    @Test
    fun givenGameId_whenGetGameStreamData_thenReturnStreamDataIsAllSameGameId() = runBlocking {
        // Given
        val mapleStoryId = "19976"

        // When
        val gameStreamData = sut.getGameStreamData(gameId = mapleStoryId).streamApiData

        // Then
        Assert.assertTrue(gameStreamData.all { it.gameId == mapleStoryId })
    }

}
