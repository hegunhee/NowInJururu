package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthTokenApi
import com.hegunhee.data.network.TwitchSearchDataApi
import com.hegunhee.data.network.TwitchStreamDataApi
import com.hegunhee.data.network.TwitchStreamerDataApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun getMoshi() : Moshi{
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

internal fun getRetrofit(moshi : Moshi, baseUrl : String) : Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

internal fun Retrofit.getTokenApi() : TwitchAuthTokenApi {
    return create(TwitchAuthTokenApi::class.java)
}

internal fun Retrofit.getSearchApi() : TwitchSearchDataApi{
    return create(TwitchSearchDataApi::class.java)
}

internal fun Retrofit.getStreamApi() : TwitchStreamDataApi {
    return create(TwitchStreamDataApi::class.java)
}

internal fun Retrofit.getStreamerDataApi() : TwitchStreamerDataApi {
    return create(TwitchStreamerDataApi::class.java)
}