package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoVideoSearchResponse(
    @Json(name = "documents")val kakaoVideoSearchData: List<KakaoVideoSearchData>,
    @Json(name = "meta")val meta: Meta
)