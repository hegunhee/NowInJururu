package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoImageSearchData(
    @Json(name = "collection") val sourceType: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "display_sitename") val displaySiteName: String,
    @Json(name = "doc_url") val url: String,
    @Json(name = "height") val height: Int,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "thumbnail_url") val thumbNailUrl: String,
    @Json(name = "width") val width: Int
)