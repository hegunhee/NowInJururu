package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchAuthTokenBaseUrl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class TwitchTokenUnitTest {
    private lateinit var tokenApi : TwitchAuthService
    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        tokenApi = getRetrofit(moshi = moshi,baseUrl = TwitchAuthTokenBaseUrl).getTwitchAuthService()
    }
    @Test
    fun `get twitch token test`() {

        runBlocking {
            runCatching {
                tokenApi.getAuthToken()
            }.onSuccess {
                println("authToken = $it")
                println("tokenFormat = ${it.getFormattedToken()}")
                assert(true)
            }.onFailure {
                println(it.toString())
                assert(false)
            }

        }
    }
}