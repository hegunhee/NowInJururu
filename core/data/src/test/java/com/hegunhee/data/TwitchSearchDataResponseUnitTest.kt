package com.hegunhee.data

import com.hegunhee.data.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class TwitchSearchDataResponseUnitTest {

    private lateinit var tokenApi : TwitchAuthService
    private lateinit var twitchService : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        tokenApi = getTwitchAuthRetrofit(moshi = moshi).getTwitchAuthService()
        twitchService = getTwitchGetRetrofit(moshi = moshi).getTwitchService()
    }

    @Test
    fun `get search data response`() {
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken().getFormattedToken()
                twitchService.getSearchData(authorization = token, streamerName = "주르르")
            }.onSuccess {
                println(it.searchApiDataList.toString())
                assert(true)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}