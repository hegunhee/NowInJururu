package com.hegunhee.feature.streamer

import com.hegunhee.domain.model.StreamDataType

fun List<StreamDataType>.toStreamViewTypeData() : List<StreamerViewType> {
    val streamData = this
    val streamerViewTypeList = mutableListOf<StreamerViewType>()
    if(streamData.any { it is StreamDataType.OnlineData }) {
        streamerViewTypeList.add(StreamerViewType.OnlineStreamerHeader(streamData.filterIsInstance<StreamDataType.OnlineData>().size))
    }
    streamerViewTypeList.addAll(streamData.filterIsInstance<StreamDataType.OnlineData>().map { it.toOnlineStreamer() })
    if(streamData.any{it is StreamDataType.OfflineData}){
        streamerViewTypeList.add(StreamerViewType.OfflineStreamerHeader(streamData.filterIsInstance<StreamDataType.OfflineData>().size))
    }
    streamerViewTypeList.addAll(streamData.filterIsInstance<StreamDataType.OfflineData>().map { it.toOfflineStreamer() })
    return streamerViewTypeList
}

fun StreamDataType.OnlineData.toOnlineStreamer() : StreamerViewType.OnlineStreamer {
    return StreamerViewType.OnlineStreamer(
        gameId = gameId,
        gameName = gameName,
        id = id,
        isMature = isMature,
        language = language,
        startedAt = startedAt,
        tags = tags,
        thumbnailUrl = thumbnailUrl,
        profileUrl = profileUrl,
        title = title,
        type = type,
        userId = userId,
        userLogin = streamerId,
        userName = streamerName,
        viewerCount = viewerCount
    )
}

fun StreamDataType.OfflineData.toOfflineStreamer() : StreamerViewType.OfflineStreamer {
    return StreamerViewType.OfflineStreamer(
        userLogin = streamerId,
        userName = streamerName,
        profileUrl = profileUrl
    )
}