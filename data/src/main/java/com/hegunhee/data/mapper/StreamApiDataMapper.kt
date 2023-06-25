package com.hegunhee.data.mapper

import com.hegunhee.data.data.json.StreamApiData
import com.hegunhee.domain.model.StreamDataType

fun StreamApiData.toStreamData(profileUrl : String): StreamDataType.OnlineData {
    return StreamDataType.OnlineData(
        gameId = gameId,
        gameName = gameName,
        id = id,
        isMature = isMature,
        language = language,
        startedAt = startedAt,
        tags = tags,
        thumbnailUrl = thumbnailUrl.toThumbNailSize(),
        profileUrl = profileUrl,
        title = title,
        type = type,
        userId = userId,
        userLogin = streamerId,
        userName = streamerName,
        viewerCount = viewerCount.toString()
    )
}

fun String.toThumbNailSize(size : Int = 100) : String{
    return this.replace("{width}","$size").replace("{height}","$size")
}
fun String.toSmallProfileSize(size : Int = 30) : String {
    return this.replace("{width}","$size").replace("{height}","$size")
}