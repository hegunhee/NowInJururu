package com.hegunhee.data.Test

import com.hegunhee.domain.Test.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class TestRepositoryModule {

    @Singleton
    @Binds
    abstract fun providesTestRepository(defaultRepository : TestDefaultRepository) : TestRepository
}