package com.hegunhee.data.di

import com.hegunhee.data.BuildConfig
import com.hegunhee.data.network.*
import com.hegunhee.data.util.TwitchAuthMoshiName
import com.hegunhee.data.util.TwitchGetMoshiName
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named(TwitchAuthMoshiName)
    fun provideTwitchAuthMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    @Named(TwitchGetMoshiName)
    fun provideTwitchGetServiceMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideTwitchAuthService(
        @Named(TwitchAuthMoshiName) moshi : Moshi
    ) : TwitchAuthService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TwitchAuthBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchService(
        @Named(TwitchGetMoshiName) moshi : Moshi
    ) : TwitchService{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(TwitchAuthInterceptor()))
            .build()
            .create(TwitchService::class.java)
    }

    private fun provideOkHttpClient(vararg interceptor: Interceptor) : OkHttpClient =
        OkHttpClient.Builder().run {
            interceptor.forEach { addInterceptor(it) }
            build()
        }

    private class TwitchAuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("client-id", BuildConfig.TwitchClientId)
                .build()
            proceed(newRequest)
        }
    }
}