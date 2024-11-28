package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchStreamApiDataResponseUnitTest {

    private lateinit var twitchService : TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        twitchService = getTwitchGetRetrofit(getMoshi()).getTwitchService()
    }

    @Test
    fun `get Stream Data`() {
        runBlocking {
            runCatching {
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
