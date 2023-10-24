package com.hegunhee.data.mapper

import com.hegunhee.data.data.json.twitch.StreamApiData
import com.hegunhee.domain.model.StreamDataType

fun StreamApiData.toStreamData(profileUrl : String, thumbNailWidth : Int = 100, thumbNailHeight : Int = 100): StreamDataType.OnlineData {
    return StreamDataType.OnlineData(
        gameId = gameId,
        gameName = gameName,
        id = id,
        isMature = isMature,
        language = language,
        startedAt = startedAt,
        tags = tags ?: emptyList(),
        thumbnailUrl = thumbnailUrl.toThumbNailSize(width = thumbNailWidth,height = thumbNailHeight),
        profileUrl = profileUrl,
        title = title,
        type = type,
        userId = userId,
        streamerId = streamerId,
        streamerName = streamerName,
        viewerCount = viewerCount.toString()
    )
}

fun String.toThumbNailSize(width : Int = 100, height : Int = 100) : String{
    return this.replace("{width}","$width").replace("{height}","$height")
}
fun String.toSmallProfileSize(size : Int = 30) : String {
    return this.replace("{width}","$size").replace("{height}","$size")
}