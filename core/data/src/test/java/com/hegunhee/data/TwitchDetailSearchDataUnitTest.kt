package com.hegunhee.data

import com.hegunhee.data.mapper.toSearchData
import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchDetailSearchDataUnitTest {

    private lateinit var tokenApi : TwitchAuthService
    private lateinit var twitchService : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        tokenApi = getTwitchAuthRetrofit(moshi = moshi).getTwitchAuthService()
        twitchService = getTwitchGetRetrofit(moshi = moshi).getTwitchService()
    }

    @Test
    fun `get detail search data`() {
        val query = "cotton__123"
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken().getFormattedToken()
                twitchService.getSearchData(authorization = token, streamerName = query).searchApiDataList.first { it.streamerId == query }.toSearchData()
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