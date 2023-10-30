package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoWebSearchData(
    @Json(name = "contents") val contents: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String
)