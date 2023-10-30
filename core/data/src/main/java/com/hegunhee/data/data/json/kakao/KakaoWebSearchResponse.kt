package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoWebSearchResponse(
    @Json(name = "documents") val kakaoWebSearchData: List<KakaoWebSearchData>,
    @Json(name = "meta") val meta: Meta
)