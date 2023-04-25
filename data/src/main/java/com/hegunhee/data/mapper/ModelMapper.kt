package com.hegunhee.data.mapper

import com.hegunhee.data.data.json.StreamApiData
import com.hegunhee.domain.model.StreamDataType.StreamData

fun StreamApiData.toStreamData(): StreamData {
    return StreamData(
        gameId = gameId,
        gameName = gameName,
        id = id,
        isMature = isMature,
        language = language,
        startedAt = startedAt,
        tags = tags,
        thumbnailUrl = thumbnailUrl,
        title = title,
        type = type,
        userId = userId,
        userLogin = userLogin,
        userName = userName,
        viewerCount = viewerCount.toString()
    )
}