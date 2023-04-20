package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchAuthTokenBaseUrl
import com.hegunhee.data.network.TwitchStreamDataApi
import com.hegunhee.data.network.TwitchStreamDataBaseUrl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TwitchStreamDataResponseUnitTest {

    @Test
    fun `get Stream Data`() {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val tokenRetrofit = Retrofit.Builder().baseUrl(TwitchAuthTokenBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthTokenApi::class.java)


        val StreamDataRetrofit = Retrofit.Builder().baseUrl(TwitchStreamDataBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchStreamDataApi::class.java)

        runBlocking {
            runCatching {
                val token = tokenRetrofit.getAuthToken().accessToken
                println("Bearer $token")
                StreamDataRetrofit.getStreamData(Authorization = "Bearer $token")
            }.onSuccess {
                println("authToken = $it")
                assert(true)
            }.onFailure {
                println(it.toString())
                assert(false)
            }

        }
    }
}