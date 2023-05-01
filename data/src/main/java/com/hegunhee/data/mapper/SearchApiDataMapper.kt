package com.hegunhee.data.mapper

import com.hegunhee.data.data.json.SearchApiData
import com.hegunhee.domain.model.SearchData


fun SearchApiData.toSearchData(): SearchData {
    return SearchData(
        streamerLogin = broadcasterLogin,
        streamerId = displayName,
        gameName = gameName,
        isLive = isLive,
        tags = tags,
        profileUrl = profileUrl.toThumbNailSize(),
        title = title
    )
}