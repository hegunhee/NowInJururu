package com.hegunhee.feature.streamer

import com.hegunhee.domain.model.StreamDataType

fun List<StreamDataType>.toStreamViewTypeData() : List<StreamerViewType> {
    val streamData = this
    val streamerViewTypeList = mutableListOf<StreamerViewType>()
    if(streamData.any { it is StreamDataType.StreamData }) {
        streamerViewTypeList.add(StreamerViewType.LiveStreamerHeader(streamData.filterIsInstance<StreamDataType.StreamData>().size))
    }
    streamerViewTypeList.addAll(streamData.filterIsInstance<StreamDataType.StreamData>().map { it.toLiveStreamer() })
    if(streamData.any{it is StreamDataType.EmptyData}){
        streamerViewTypeList.add(StreamerViewType.UnLiveStreamerHeader(streamData.filterIsInstance<StreamDataType.EmptyData>().size))
    }
    streamerViewTypeList.addAll(streamData.filterIsInstance<StreamDataType.EmptyData>().map { it.toUnLiveStreamer() })
    return streamerViewTypeList
}

fun StreamDataType.StreamData.toLiveStreamer() : StreamerViewType.LiveStreamer {
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

fun StreamDataType.EmptyData.toUnLiveStreamer() : StreamerViewType.UnLiveStreamer {
    return StreamerViewType.UnLiveStreamer(
        userLogin = userLogin,
        userName = userName,
        profileUrl = profileUrl
    )
}