package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoVideoSearchData(
    @Json(name = "author") val author: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "play_time") val playTime: Int,
    @Json(name = "thumbnail") val thumbNailUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String
)