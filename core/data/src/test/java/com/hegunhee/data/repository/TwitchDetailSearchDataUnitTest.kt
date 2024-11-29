package com.hegunhee.data.repository

import com.hegunhee.data.di.NetworkModule.provideTwitchAuthMoshi
import com.hegunhee.data.di.NetworkModule.provideTwitchAuthService
import com.hegunhee.data.di.NetworkModule.provideTwitchService
import com.hegunhee.data.mapper.toSearchData
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchDetailSearchDataUnitTest {

    private lateinit var twitchService: TwitchService

    @Before
    fun initMoshiAndRetrofit() {
        twitchService =  provideTwitchService(
            moshi = provideTwitchAuthMoshi(),
            twitchAuthService = provideTwitchAuthService(provideTwitchAuthMoshi()),
        )
    }

    @Test
    fun `get detail search data`() {
        val query = "cotton__123"
        runBlocking {
            runCatching {
                twitchService.getSearchData(streamerName = query).searchApiDataList.first { it.streamerId == query }.toSearchData()
            }.onSuccess {
                println(it.toString())
                assert(true)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

}
