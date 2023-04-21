package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchAuthTokenBaseUrl
import com.hegunhee.data.network.TwitchStreamDataApi
import com.hegunhee.data.network.TwitchStreamDataBaseUrl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TwitchStreamApiDataResponseUnitTest {

    private lateinit var moshi : Moshi
    private lateinit var tokenApi : TwitchAuthTokenApi
    private lateinit var streamDataApi : TwitchStreamDataApi

    @Before
    fun initMoshiAndRetrofit()  {
        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        tokenApi = Retrofit.Builder().baseUrl(TwitchAuthTokenBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthTokenApi::class.java)
        streamDataApi = Retrofit.Builder().baseUrl(TwitchStreamDataBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchStreamDataApi::class.java)
    }

    @Test
    fun `get Stream Data`() {
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken()
                println("Bearer $token")
                streamDataApi.getStreamData(Authorization = token.getFormattedToken())
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
                streamDataApi.getStreamData(Authorization = token.getFormattedToken())
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