package com.hegunhee.data.data.json.twitch

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchApiDataResponse(
    @Json(name = "data") val searchApiDataList: List<SearchApiData>,
    @Json(name = "pagination") val pagination: Pagination?
)