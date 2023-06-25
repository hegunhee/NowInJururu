package com.hegunhee.data.mapper

import com.hegunhee.data.data.json.SearchApiData
import com.hegunhee.domain.model.SearchData


fun SearchApiData.toSearchData(): SearchData {
    return SearchData(
        streamerLogin = streamerId,
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