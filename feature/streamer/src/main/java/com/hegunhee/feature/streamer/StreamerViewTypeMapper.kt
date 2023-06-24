package com.hegunhee.feature.streamer

import com.hegunhee.domain.model.StreamDataType

fun List<StreamDataType>.toStreamViewTypeData() : List<StreamerViewType> {
    val groupDataType = this.groupBy { it is StreamDataType.StreamData }.values.toList()
    val streamerViewTypeList = mutableListOf<StreamerViewType>()
    if(groupDataType.isNotEmpty()){
        if(groupDataType.size == 1){
            when(groupDataType[0][0]) {
                is StreamDataType.StreamData -> {
                    streamerViewTypeList.add(StreamerViewType.LiveStreamerHeader(groupDataType[0].size))
                    val liveStreamList = groupDataType[0].filterIsInstance<StreamDataType.StreamData>().map { it.toLiveStreamer() }
                    streamerViewTypeList.addAll(liveStreamList)
                }
                is StreamDataType.EmptyData -> {
                    streamerViewTypeList.add(StreamerViewType.UnLiveStreamerHeader(groupDataType[0].size))
                    val unLiveStreamList = groupDataType[0].filterIsInstance<StreamDataType.EmptyData>().map { it.toUnLiveStreamer() }
                    streamerViewTypeList.addAll(unLiveStreamList)
                }
            }
        }else if(groupDataType.size == 2) {
            streamerViewTypeList.add(StreamerViewType.LiveStreamerHeader(groupDataType[0].size))
            val liveStreamList = groupDataType[0].filterIsInstance<StreamDataType.StreamData>().map { it.toLiveStreamer() }
            streamerViewTypeList.addAll(liveStreamList)
            streamerViewTypeList.add(StreamerViewType.UnLiveStreamerHeader(groupDataType[1].size))
            val unLiveStreamList = groupDataType[1].filterIsInstance<StreamDataType.EmptyData>().map { it.toUnLiveStreamer() }
            streamerViewTypeList.addAll(unLiveStreamList)
        }
    }
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