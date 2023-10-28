package com.hegunhee.data

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
        twitchAuthService = getRetrofit(moshi = moshi, baseUrl = BuildConfig.TwitchAuthBaseUrl).getTwitchAuthService()
        twitchService = getRetrofit(moshi = moshi,baseUrl = BuildConfig.TwitchGetBaseUrl).getTwitchService()
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
                twitchService.getGameStreamData(authorization = token.getFormattedToken(),gameId = "19976").streamApiData
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
            val gameStreamList = twitchService.getGameStreamData(authorization = token,gameId = "19976").streamApiData
            if(gameStreamList.isEmpty()){
                println("data is empty")
                assert(true)
            }else{
                val streamerInfoList = twitchService.getStreamerData(userLogin = gameStreamList.map { it.streamerId }.toTypedArray(), authorization = token).streamerApiDataList
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