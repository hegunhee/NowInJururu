package com.hegunhee.data.di

import com.hegunhee.data.BuildConfig
import com.hegunhee.data.network.*
import com.hegunhee.data.util.KakaoSearchMoshiName
import com.hegunhee.data.util.TwitchAuthMoshiName
import com.hegunhee.data.util.TwitchGetMoshiName
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

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
    @Named(KakaoSearchMoshiName)
    fun provideKakaoSearchServiceMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

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
        @Named(TwitchGetMoshiName) moshi : Moshi,
        twitchAuthService: TwitchAuthService
    ) : TwitchService{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TwitchGetBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(TwitchAuthInterceptor(twitchAuthService)))
            .build()
            .create(TwitchService::class.java)
    }

    @Singleton
    @Provides
    fun provideKakaoService(
        @Named(KakaoSearchMoshiName) moshi : Moshi
    ) : KakaoService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.KakaoBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(KakaoAuthInterceptor()))
            .build()
            .create(KakaoService::class.java)
    }


    private fun provideOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        val debugInterceptor = HttpLoggingInterceptor()
        debugInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder().run {
            addNetworkInterceptor(debugInterceptor)
            interceptors.forEach { addInterceptor(it) }
            build()
        }
    }

    // OkHttp는 메인쓰레드가 아닌 여러개의 스레드를 사용하므로
    // runBlocking을 사용해도 안전함
    // https://github.com/hegunhee/NowInJururu/issues/55 를 참고

    private class TwitchAuthInterceptor(private val twitchAuthService: TwitchAuthService) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val token = runBlocking { twitchAuthService.getAuthToken() }
            val newRequest = request().newBuilder()
                .addHeader("client-id", BuildConfig.TwitchClientId)
                .addHeader("Authorization",token.getFormattedToken())
                .build()
            proceed(newRequest)
        }
    }

    private class KakaoAuthInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization",BuildConfig.KakaoAuthKey)
                .build()
            proceed(newRequest)
        }
    }
}
