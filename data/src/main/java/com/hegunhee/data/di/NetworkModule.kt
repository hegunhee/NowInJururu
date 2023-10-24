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
    fun provideTwitchAuthTokenApi(
        @Named(TwitchAuthMoshiName) moshi : Moshi
    ) : TwitchAuthService {
        return Retrofit.Builder()
            .baseUrl(TwitchAuthTokenBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TwitchAuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchStreamDataApi(
        @Named(TwitchGetMoshiName) moshi : Moshi
    ) : TwitchStreamDataApi{
        return Retrofit.Builder()
            .baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(InterceptorForAuth()))
            .build()
            .create(TwitchStreamDataApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchSearchDataApi(
        @Named(TwitchGetMoshiName) moshi : Moshi
    ) : TwitchSearchDataApi{
        return Retrofit.Builder()
            .baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(InterceptorForAuth()))
            .build()
            .create(TwitchSearchDataApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchStreamerDataApi(
        @Named(TwitchGetMoshiName) moshi : Moshi
    ) : TwitchStreamerDataApi {
        return Retrofit.Builder()
            .baseUrl(TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(InterceptorForAuth()))
            .build()
            .create(TwitchStreamerDataApi::class.java)
    }

    private fun provideOkHttpClient(vararg interceptor: Interceptor) : OkHttpClient =
        OkHttpClient.Builder().run {
            interceptor.forEach { addInterceptor(it) }
            build()
        }

    private class InterceptorForAuth : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("client-id", BuildConfig.clientId)
                .build()
            proceed(newRequest)
        }
    }
}