package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class TwitchStreamApiDataResponseUnitTest {

    private lateinit var twitchAuthService : TwitchAuthService
    private lateinit var twitchService : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        twitchAuthService = getTwitchAuthRetrofit(moshi = moshi).getTwitchAuthService()
        twitchService = getTwitchGetRetrofit(moshi = moshi).getTwitchService()
    }

    @Test
    fun `get Stream Data`() {
        runBlocking {
            runCatching {
                val token = twitchAuthService.getAuthToken()
                println("Bearer $token")
                twitchService.getStreamData(streamerId = "cotton__123")
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
                val token = twitchAuthService.getAuthToken()
                println("Bearer $token")
                twitchService.getStreamData(streamerId = "cotton__123")
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