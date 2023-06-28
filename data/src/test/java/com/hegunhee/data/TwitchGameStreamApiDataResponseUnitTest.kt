package com.hegunhee.data

import com.hegunhee.data.mapper.toSearchDataList
import com.hegunhee.data.mapper.toStreamData
import com.hegunhee.data.network.*
import com.hegunhee.domain.model.StreamDataType
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchGameStreamApiDataResponseUnitTest {

    private lateinit var tokenApi : TwitchAuthTokenApi
    private lateinit var streamDataApi : TwitchStreamDataApi
    private lateinit var streamerDataApi : TwitchStreamerDataApi

    @Before
    fun initMoshiAndRetrofit()  {
        val moshi = getMoshi()
        tokenApi = getRetrofit(moshi = moshi, baseUrl = TwitchAuthTokenBaseUrl).getTokenApi()
        streamDataApi = getRetrofit(moshi = moshi,baseUrl = TwitchGetBaseUrl).getStreamApi()
        streamerDataApi = getRetrofit(moshi = moshi,baseUrl = TwitchGetBaseUrl).getStreamerDataApi()
    }

    /**
     * mapleStory = "19976"
     */
    @Test
    fun `get maple story stream`() {
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken()
                println("Bearer $token")
                streamDataApi.getGameStreamData(authorization = token.getFormattedToken(),gameId = "19976").streamApiData
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
            val token = tokenApi.getAuthToken().getFormattedToken()
            println("Bearer $token")
            val gameStreamList = streamDataApi.getGameStreamData(authorization = token,gameId = "19976").streamApiData
            if(gameStreamList.isEmpty()){
                println("data is empty")
                assert(true)
            }else{
                val streamerInfoList = streamerDataApi.getStreamerData(userLogin = gameStreamList.map { it.streamerId }.toTypedArray(), authorization = token).streamerApiDataList
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