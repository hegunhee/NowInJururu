package com.hegunhee.data

import com.hegunhee.data.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class TwitchSearchDataResponseUnitTest {

    private lateinit var tokenApi : TwitchAuthService
    private lateinit var searchApi : TwitchSearchDataApi

    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        tokenApi = getRetrofit(moshi = moshi,baseUrl = TwitchAuthTokenBaseUrl).getTokenApi()
        searchApi = getRetrofit(moshi = moshi,baseUrl = TwitchGetBaseUrl).getSearchApi()
    }

    @Test
    fun `get search data response`() {
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken().getFormattedToken()
                searchApi.getSearchData(authorization = token, streamerName = "주르르")
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