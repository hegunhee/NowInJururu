package com.hegunhee.data.data.json.twitch

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StreamerApiDataResponse(
    @Json(name = "data") val streamerApiDataList : List<StreamerApiData>
)