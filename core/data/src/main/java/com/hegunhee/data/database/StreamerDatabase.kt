package com.hegunhee.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hegunhee.data.database.dao.StreamerDao
import com.hegunhee.data.database.entity.StreamerEntity

@Database(entities = [StreamerEntity::class],version = 1, exportSchema = false)
abstract class StreamerDatabase : RoomDatabase(){

    abstract fun streamerDao() : StreamerDao

    companion object {
        const val DB_NAME = "StreamerDatabase.db"
    }
}