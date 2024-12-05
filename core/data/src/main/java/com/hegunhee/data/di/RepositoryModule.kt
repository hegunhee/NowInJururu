package com.hegunhee.data.di

import com.hegunhee.data.repository.kakao.DefaultKakaoPagingRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchGameStreamRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchPagingRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchSearchStreamerRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchStreamRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchUpdateStreamerRepository
import com.hegunhee.domain.repository.kakao.KakaoPagingRepository
import com.hegunhee.domain.repository.twitch.TwitchGameStreamRepository
import com.hegunhee.domain.repository.twitch.TwitchPagingRepository
import com.hegunhee.domain.repository.twitch.TwitchSearchStreamerRepository
import com.hegunhee.domain.repository.twitch.TwitchStreamRepository
import com.hegunhee.domain.repository.twitch.TwitchUpdateStreamerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun provideTwitchStreamRepository(defaultTwitchStreamRepository: DefaultTwitchStreamRepository): TwitchStreamRepository

    @Binds
    @Singleton
    abstract fun provideTwitchGameStreamRepository(defaultTwitchGameStreamRepository: DefaultTwitchGameStreamRepository): TwitchGameStreamRepository

    @Binds
    @Singleton
    abstract fun provideTwitchSearchStreamerDataRepository(defaultTwitchSearchStreamerRepository: DefaultTwitchSearchStreamerRepository): TwitchSearchStreamerRepository

    @Binds
    @Singleton
    abstract fun provideTwitchUpdateStreamerRepository(defaultTwitchUpdateStreamerRepository: DefaultTwitchUpdateStreamerRepository): TwitchUpdateStreamerRepository

    @Binds
    @Singleton
    abstract fun provideTwitchPagingRepository(defaultTwitchPagingRepository: DefaultTwitchPagingRepository): TwitchPagingRepository

    @Binds
    @Singleton
    abstract fun provideKakaoPagingRepository(defaultKakaoPagingRepository: DefaultKakaoPagingRepository): KakaoPagingRepository
}
