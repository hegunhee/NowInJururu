package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.data.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchGameStreamApiDataResponseUnitTest {

    private lateinit var twitchService: TwitchService

    @Before
    fun initMoshiAndRetrofit() {
        twitchService = getTwitchGetRetrofit(getMoshi()).getTwitchService()
    }

    /**
     * mapleStory = "19976"
     */
    @Test
    fun `get maple story stream`() {
        runBlocking {
            runCatching {
                twitchService.getGameStreamData(gameId = "19976").streamApiData
            }.onSuccess {
                println(it.toString())
                assert(true)
            }.onFailure {
                println(it.toString())
                assert(false)
            }
        }
    }

    @Test
    fun `transfer gameStreamApiData to model data`() {
        runBlocking {
            val gameStreamList = twitchService.getGameStreamData(gameId = "19976").streamApiData
            if(gameStreamList.isEmpty()){
                println("data is empty")
                assert(true)
            }else{
                val streamerInfoList = twitchService.getStreamerData(streamerId = gameStreamList.map { it.streamerId }.toTypedArray()).streamerApiDataList
                gameStreamList.mapIndexed { index, streamApiData ->
                    streamApiData.toStreamData(streamerInfoList[index].profileImageUrl)
                }.forEach {
                    println(it.toString())
                }
                assert(true)
            }
        }
    }

}
