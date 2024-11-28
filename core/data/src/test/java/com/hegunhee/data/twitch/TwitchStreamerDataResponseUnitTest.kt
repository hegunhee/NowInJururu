package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class TwitchStreamerDataResponseUnitTest {

    private lateinit var twitchService: TwitchService

    @Before
    fun initMoshiAndApi() {
        twitchService = getTwitchGetRetrofit(getMoshi()).getTwitchService()
    }

    @Test
    fun `one streamer data (cotton__123)`() {
        runBlocking {
            runCatching {
                val streamer = listOf("cotton__123")
                twitchService.getStreamerData(streamerId =streamer.toTypedArray())
            }.onSuccess {response ->
                println(response.toString())
                if(response.streamerApiDataList[0].streamerName == "주르르"){
                    assert(true)
                }else{
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `two streamer data (cotton__123, viichan6)` () {
        runBlocking {
            val streamer = listOf("cotton__123","viichan6")
            runCatching {
                twitchService.getStreamerData(streamerId =streamer.toTypedArray())
            }.onSuccess {response ->
                println(response.toString())
                val findStreamerId = response.streamerApiDataList.map { it.streamerId }
                assertEquals(streamer,findStreamerId)
            }.onFailure {
                println(it.message)
                fail()
            }
        }
    }

}
