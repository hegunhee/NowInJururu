package com.hegunhee.data.di

import com.hegunhee.data.repository.DefaultRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchGameStreamRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchSearchStreamerRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchStreamRepository
import com.hegunhee.data.repository.twitch.DefaultTwitchUpdateStreamerRepository
import com.hegunhee.domain.repository.Repository
import com.hegunhee.domain.repository.twitch.TwitchGameStreamRepository
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
    abstract fun provideRepository(defaultRepository: DefaultRepository): Repository

    @Binds
    @Singleton
    abstract fun provideTwitchStreamRepository(defaultTwitchStreamRepository: DefaultTwitchStreamRepository): TwitchStreamRepository

    @Binds
    @Singleton
    abstract fun provideTwitchGameStreamRepository(defaultTwitchGameStreamRepository: DefaultTwitchGameStreamRepository): TwitchGameStreamRepository

    @Binds
    @Singleton
    abstract fun provideTwitchSearchStreamerDataRepository(defaultTwitchSearchStreamerRepository: DefaultTwitchSearchStreamerRepository) : TwitchSearchStreamerRepository

    @Binds
    @Singleton
    abstract fun provideTwitchUpdateStreamerRepository(defaultTwitchUpdateStreamerRepository: DefaultTwitchUpdateStreamerRepository): TwitchUpdateStreamerRepository

}
