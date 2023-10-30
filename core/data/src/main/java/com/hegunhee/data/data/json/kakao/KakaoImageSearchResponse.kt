package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoImageSearchResponse(
    @Json(name = "documents") val kakaoImageSearchData: List<KakaoImageSearchData>,
    @Json(name = "meta") val meta: Meta
)