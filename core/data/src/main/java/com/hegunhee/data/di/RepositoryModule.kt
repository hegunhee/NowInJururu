package com.hegunhee.data.di

import com.hegunhee.data.repository.DefaultRepository
import com.hegunhee.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(defaultRepository : DefaultRepository) : Repository
}