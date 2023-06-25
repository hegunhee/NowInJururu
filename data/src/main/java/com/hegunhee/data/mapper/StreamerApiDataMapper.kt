package com.hegunhee.data.mapper

import com.hegunhee.data.database.entity.StreamerEntity
import com.hegunhee.domain.model.StreamerData

fun StreamerEntity.toStreamerData() : StreamerData {
    return StreamerData(streamerId = streamerLogin,isAlert = isAlert)
}

fun List<StreamerEntity>.toStreamerDataList() : List<StreamerData> {
    return map{ it.toStreamerData() }
}

fun StreamerData.toStreamerEntity() : StreamerEntity {
    return StreamerEntity(streamerLogin = streamerId, isAlert = isAlert)
}