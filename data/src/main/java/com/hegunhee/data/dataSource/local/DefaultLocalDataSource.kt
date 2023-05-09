package com.hegunhee.data.dataSource.local

import com.hegunhee.data.database.dao.StreamerDao
import com.hegunhee.data.database.entity.StreamerEntity
import javax.inject.Inject

class DefaultLocalDataSource @Inject constructor(private val streamerDao : StreamerDao) : LocalDataSource {
    override suspend fun insertStreamer(streamerEntity: StreamerEntity) {
        streamerDao.insertStreamer(streamerEntity)
    }

    override suspend fun updateStreamer(streamerEntity: StreamerEntity) {
        streamerDao.updateStreamer(streamerEntity)
    }

    override suspend fun getAllStreamerList(): List<StreamerEntity> {
        return streamerDao.getAllStreamerEntityList()
    }
}