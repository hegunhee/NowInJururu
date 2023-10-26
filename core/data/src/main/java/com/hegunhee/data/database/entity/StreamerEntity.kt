package com.hegunhee.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StreamerEntity(
    @PrimaryKey val streamerLogin : String,
    val isAlert : Boolean = false
)