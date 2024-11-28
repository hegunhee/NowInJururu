package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchDataTest {

    private lateinit var sut : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        sut = getTwitchGetRetrofit(getMoshi()).getTwitchService()
    }

    @Test
    fun givenStreamerName_whenSearchData_thenReturnStreamerData() = runBlocking {
        // Given
        val streamerName = "주르르"

        // When
        val searchDataList = sut.getSearchData(streamerName = streamerName).searchApiDataList

        // Then
        Assert.assertTrue(searchDataList.map { it.streamerName }.contains(streamerName))
    }

}
