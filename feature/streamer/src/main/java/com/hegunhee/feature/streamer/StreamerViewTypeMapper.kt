package com.hegunhee.feature.streamer

import com.hegunhee.domain.model.StreamDataType

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