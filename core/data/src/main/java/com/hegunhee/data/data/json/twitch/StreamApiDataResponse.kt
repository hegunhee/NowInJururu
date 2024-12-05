package com.hegunhee.data.data.json.twitch

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StreamApiDataResponse(
    @Json(name = "data") val streamApiData: List<StreamApiData> = emptyList(),
    @Json(name = "pagination") val pagination: Pagination?
) {
    fun isEmpty(): Boolean {
        return streamApiData.isEmpty()
    }
}