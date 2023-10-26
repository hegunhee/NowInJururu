package com.hegunhee.data

import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchService
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

internal fun Retrofit.getTwitchAuthService() : TwitchAuthService {
    return create(TwitchAuthService::class.java)
}

internal fun Retrofit.getTwitchService() : TwitchService {
    return create(TwitchService::class.java)
}