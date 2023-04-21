package com.hegunhee.data.di

import com.hegunhee.data.dataSource.remote.DefaultRemoteDataSource
import com.hegunhee.data.dataSource.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(defaultRemoteDataSource: DefaultRemoteDataSource) : RemoteDataSource


}