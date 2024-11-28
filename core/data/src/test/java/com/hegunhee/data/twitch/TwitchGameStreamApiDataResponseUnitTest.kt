package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchAuthRetrofit
import com.hegunhee.data.getTwitchAuthService
import com.hegunhee.data.getTwitchGetRetrofit
import com.hegunhee.data.getTwitchService
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.data.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchGameStreamApiDataResponseUnitTest {

    private lateinit var twitchAuthService : TwitchAuthService
    private lateinit var twitchService: TwitchService

    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        twitchAuthService = getTwitchAuthRetrofit(moshi = moshi).getTwitchAuthService()
        twitchService = getTwitchGetRetrofit(moshi).getTwitchService()
    }

    /**
     * mapleStory = "19976"
     */
    @Test
    fun `get maple story stream`() {
        runBlocking {
            runCatching {
                val token = twitchAuthService.getAuthToken()
                println("Bearer $token")
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
            val token = twitchAuthService.getAuthToken().getFormattedToken()
            println("Bearer $token")
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