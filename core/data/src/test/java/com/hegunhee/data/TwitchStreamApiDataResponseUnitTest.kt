package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchAuthTokenBaseUrl
import com.hegunhee.data.network.TwitchGetBaseUrl
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class TwitchStreamApiDataResponseUnitTest {

    private lateinit var tokenApi : TwitchAuthService
    private lateinit var twitchService : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        tokenApi = getRetrofit(moshi = moshi, baseUrl = TwitchAuthTokenBaseUrl).getTwitchAuthService()
        twitchService = getRetrofit(moshi = moshi,baseUrl = TwitchGetBaseUrl).getTwitchService()
    }

    @Test
    fun `get Stream Data`() {
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken()
                println("Bearer $token")
                twitchService.getStreamData(authorization = token.getFormattedToken(), userLogin = "cotton__123")
            }.onSuccess {
                println("authToken = $it")
                assert(true)
            }.onFailure {
                println(it.toString())
                assert(false)
            }
        }
    }

    @Test
    fun `empty stream data is emptyList`() {
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken()
                println("Bearer $token")
                streamDataApi.getStreamData(authorization = token.getFormattedToken(), userLogin = "cotton__123")
            }.onSuccess {
                println("authToken = $it")
                if(it.streamApiData.isEmpty()){
                    println(it.streamApiData.toString())
                    assert(true)
                }else{
                    assert(false)
                }
            }.onFailure {
                println(it.toString())
                assert(false)
            }
        }
    }
}