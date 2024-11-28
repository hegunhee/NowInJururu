package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchAuthRetrofit
import com.hegunhee.data.getTwitchAuthService
import com.hegunhee.data.network.TwitchAuthService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class TwitchTokenUnitTest {
    private lateinit var twitchAuthService : TwitchAuthService
    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        twitchAuthService = getTwitchAuthRetrofit(moshi = moshi).getTwitchAuthService()
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