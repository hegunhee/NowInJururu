package com.hegunhee.data.api.twitch

import com.hegunhee.data.di.NetworkModule.provideTwitchAuthMoshi
import com.hegunhee.data.di.NetworkModule.provideTwitchAuthService
import com.hegunhee.data.di.NetworkModule.provideTwitchService
import com.hegunhee.data.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchDataTest {

    private lateinit var sut : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        sut = provideTwitchService(
            moshi = provideTwitchAuthMoshi(),
            twitchAuthService = provideTwitchAuthService(provideTwitchAuthMoshi()),
        )
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
