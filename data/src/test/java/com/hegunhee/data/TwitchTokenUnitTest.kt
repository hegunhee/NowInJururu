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


class TwitchTokenUnitTest {
    private lateinit var moshi : Moshi
    private lateinit var tokenApi : TwitchAuthTokenApi
    @Before
    fun initMoshiAndRetrofit()  {
        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        tokenApi = Retrofit.Builder().baseUrl(TwitchAuthTokenBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthTokenApi::class.java)
    }
    @Test
    fun `get twitch token test`() {

        runBlocking {
            runCatching {
                tokenApi.getAuthToken()
            }.onSuccess {
                println("authToken = $it")
                println("tokenFormat = ${it.getFormattedToken()}")
                assert(true)
            }.onFailure {
                println(it.toString())
                assert(false)
            }

        }
    }
}