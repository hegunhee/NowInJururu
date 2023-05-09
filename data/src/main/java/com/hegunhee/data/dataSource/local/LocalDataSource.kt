package com.hegunhee.data.dataSource.local

import com.hegunhee.data.database.entity.StreamerEntity

interface LocalDataSource {

    suspend fun insertStreamer(streamerEntity: StreamerEntity)

    suspend fun updateStreamer(streamerEntity: StreamerEntity)

    suspend fun getAllStreamerList(): List<StreamerEntity>
}