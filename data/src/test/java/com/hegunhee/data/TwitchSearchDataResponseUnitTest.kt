package com.hegunhee.data

import com.hegunhee.data.network.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TwitchSearchDataResponseUnitTest {

    private lateinit var moshi : Moshi
    private lateinit var tokenApi : TwitchAuthTokenApi
    private lateinit var searchApi : TwitchSearchDataApi

    @Before
    fun initMoshiAndRetrofit()  {
        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        tokenApi = Retrofit.Builder().baseUrl(TwitchAuthTokenBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthTokenApi::class.java)
        searchApi = Retrofit.Builder().baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchSearchDataApi::class.java)
    }

    @Test
    fun `get search data response`() {
        runBlocking {
            runCatching {
                val token = tokenApi.getAuthToken().getFormattedToken()
                searchApi.getSearchData(authorization = token, streamerName = "주르르")
            }.onSuccess {
                println(it.searchApiDataList.toString())
                assert(true)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}