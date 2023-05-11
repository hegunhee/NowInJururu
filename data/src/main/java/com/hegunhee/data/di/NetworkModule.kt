package com.hegunhee.data.di

import com.hegunhee.data.network.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideTwitchAuthTokenApi(
        moshi : Moshi
    ) : TwitchAuthTokenApi {
        return Retrofit.Builder()
            .baseUrl(TwitchAuthTokenBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthTokenApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchStreamDataApi(
        moshi : Moshi
    ) : TwitchStreamDataApi{
        return Retrofit.Builder()
            .baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchStreamDataApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchSearchDataApi(
        moshi : Moshi
    ) : TwitchSearchDataApi{
        return Retrofit.Builder()
            .baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchSearchDataApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchStreamerDataApi(
        moshi : Moshi
    ) : TwitchStreamerDataApi {
        return Retrofit.Builder()
            .baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchStreamerDataApi::class.java)
    }
}