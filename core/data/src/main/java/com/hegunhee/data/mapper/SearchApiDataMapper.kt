package com.hegunhee.data.mapper

import com.hegunhee.data.data.json.twitch.SearchApiData
import com.hegunhee.domain.model.twitch.SearchData


fun SearchApiData.toSearchData(): SearchData {
    return SearchData(
        streamerId = streamerId,
        streamerName = streamerName,
        gameName = gameName,
        isLive = isLive,
        tags = tags,
        profileUrl = profileUrl.toThumbNailSize(),
        title = title
    )
}

fun List<SearchApiData>.toSearchDataList() : List<SearchData> {
    return this.map { it.toSearchData() }
}