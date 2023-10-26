package com.hegunhee.data.di

import android.content.Context
import androidx.room.Room
import com.hegunhee.data.database.StreamerDatabase
import com.hegunhee.data.database.dao.StreamerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideStreamerDatabase(@ApplicationContext context : Context) : StreamerDatabase {
        return Room.databaseBuilder(context,StreamerDatabase::class.java,StreamerDatabase.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideStreamerDao(database : StreamerDatabase) : StreamerDao {
        return database.streamerDao()
    }
}