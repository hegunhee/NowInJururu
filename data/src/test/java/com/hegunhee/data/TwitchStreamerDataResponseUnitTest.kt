package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchAuthTokenBaseUrl
import com.hegunhee.data.network.TwitchGetBaseUrl
import com.hegunhee.data.network.TwitchStreamerDataApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TwitchStreamerDataResponseUnitTest {

    private lateinit var moshi : Moshi
    private lateinit var tokenApi : TwitchAuthTokenApi
    private lateinit var streamerApi : TwitchStreamerDataApi

    @Before
    fun initMoshiAndApi() {
        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        tokenApi = Retrofit.Builder().baseUrl(TwitchAuthTokenBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthTokenApi::class.java)
        streamerApi = Retrofit.Builder().baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchStreamerDataApi::class.java)
    }

    @Test
    fun `test one streamer data (Jururu)`() {
        runBlocking {
            runCatching {
                val streamer = listOf<String>("cotton__123")
                val token = tokenApi.getAuthToken().getFormattedToken()
                println(streamer)
                streamerApi.getStreamerData(authorization = token,userLogin =streamer.toTypedArray())
            }.onSuccess {response ->
                println(response.toString())
                if(response.streamerApiDataList[0].display_name == "주르르"){
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