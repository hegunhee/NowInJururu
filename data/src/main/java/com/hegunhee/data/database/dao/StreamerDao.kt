package com.hegunhee.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hegunhee.data.database.entity.StreamerEntity

interface StreamerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStreamer(streamerEntity: StreamerEntity)

    @Update
    suspend fun updateStreamer(streamerEntity: StreamerEntity)

    @Query("SELECT * FROM StreamerEntity")
    suspend fun getAllStreamerEntityList() : List<StreamerEntity>
    
}