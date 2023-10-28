package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class TwitchTokenUnitTest {
    private lateinit var twitchAuthService : TwitchAuthService
    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        twitchAuthService = getRetrofit(moshi = moshi,baseUrl = BuildConfig.TwitchAuthBaseUrl).getTwitchAuthService()
    }
    @Test
    fun `get twitch token test`() {

        runBlocking {
            runCatching {
                twitchAuthService.getAuthToken()
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