package com.hegunhee.feature.streamer

import com.hegunhee.domain.model.StreamDataType

fun List<StreamDataType>.toStreamViewTypeData() : List<StreamerViewType> {
    val streamData = this
    val streamerViewTypeList = mutableListOf<StreamerViewType>()
    if(streamData.any { it is StreamDataType.OnlineData }) {
        streamerViewTypeList.add(StreamerViewType.LiveStreamerHeader(streamData.filterIsInstance<StreamDataType.OnlineData>().size))
    }
    streamerViewTypeList.addAll(streamData.filterIsInstance<StreamDataType.OnlineData>().map { it.toLiveStreamer() })
    if(streamData.any{it is StreamDataType.OfflineData}){
        streamerViewTypeList.add(StreamerViewType.UnLiveStreamerHeader(streamData.filterIsInstance<StreamDataType.OfflineData>().size))
    }
    streamerViewTypeList.addAll(streamData.filterIsInstance<StreamDataType.OfflineData>().map { it.toUnLiveStreamer() })
    return streamerViewTypeList
}

fun StreamDataType.OnlineData.toLiveStreamer() : StreamerViewType.LiveStreamer {
    return StreamerViewType.LiveStreamer(
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
        userLogin = userLogin,
        userName = userName,
        viewerCount = viewerCount
    )
}

fun StreamDataType.OfflineData.toUnLiveStreamer() : StreamerViewType.UnLiveStreamer {
    return StreamerViewType.UnLiveStreamer(
        userLogin = userLogin,
        userName = userName,
        profileUrl = profileUrl
    )
}