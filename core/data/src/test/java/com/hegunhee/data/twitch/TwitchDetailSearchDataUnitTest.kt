package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.mapper.toSearchData
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchDetailSearchDataUnitTest {

    private lateinit var twitchService: TwitchService

    @Before
    fun initMoshiAndRetrofit() {
        twitchService = getTwitchGetRetrofit(getMoshi()).getTwitchService()
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
