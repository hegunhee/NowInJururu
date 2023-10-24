package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchAuthTokenBaseUrl
import com.hegunhee.data.network.TwitchGetBaseUrl
import com.hegunhee.data.network.TwitchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TwitchStreamerDataResponseUnitTest {

    private lateinit var tokenApi : TwitchAuthService
    private lateinit var twitchService: TwitchService

    @Before
    fun initMoshiAndApi() {
        val moshi = getMoshi()
        tokenApi = getRetrofit(moshi = moshi,baseUrl = TwitchAuthTokenBaseUrl).getTwitchAuthService()
        twitchService = getRetrofit(moshi = moshi,baseUrl = TwitchGetBaseUrl).getTwitchService()
    }

    @Test
    fun `one streamer data (cotton__123)`() {
        runBlocking {
            runCatching {
                val streamer = listOf<String>("cotton__123")
                val token = tokenApi.getAuthToken().getFormattedToken()
                twitchService.getStreamerData(authorization = token,userLogin =streamer.toTypedArray())
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
            runCatching {
                val streamer = listOf<String>("cotton__123","viichan6")
                val token = tokenApi.getAuthToken().getFormattedToken()
                streamerApi.getStreamerData(authorization = token,userLogin =streamer.toTypedArray())
            }.onSuccess {response ->
                println(response.toString())
                val displayNameList = response.streamerApiDataList.map { it.streamerName }
                if(displayNameList[0] == "주르르" && displayNameList[1] == "비챤_"){
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
}