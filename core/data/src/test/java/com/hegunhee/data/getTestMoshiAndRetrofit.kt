package com.hegunhee.data

import com.hegunhee.data.network.KakaoService
import com.hegunhee.data.network.TwitchAuthService
import com.hegunhee.data.network.TwitchService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun getMoshi() : Moshi {
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

internal fun getKakaoRetrofit(moshi : Moshi) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.KakaoBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(provideOkHttpClient(KakaoAuthInterceptor()))
        .build()
}

internal fun Retrofit.getKakaoService() : KakaoService {
    return create(KakaoService::class.java)
}

private fun provideOkHttpClient(vararg interceptor: Interceptor) : OkHttpClient =
    OkHttpClient.Builder().run {
        interceptor.forEach { addInterceptor(it) }
        build()
    }

private class KakaoAuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("Authorization", BuildConfig.KakaoAuthKey)
            .build()
        proceed(newRequest)
    }
}