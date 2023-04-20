package com.hegunhee.data.data.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StreamDataResponse(
    @Json(name = "data")val streamData: List<StreamData>?,
    @Json(name = "pagination")val pagination: Pagination?
)