package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * "contents": "같은 장르에 자연스럽게 녹아드면서도 자신만의 특색을 보일 수 있는 가창이 가능하다. 자세한 내용은 <b>주르르</b>/방송 역사 문서 참고하십시오. 자세한 내용은 <b>주르르</b>/방송 역사 문서 참고하십시오. 방송에 대한...",
 * "datetime": "2023-10-29T00:00:00.000+09:00",
 * "title": "<b>주르르</b> - 나무위키",
 * "url": "https://namu.wiki/w/%EC%A3%BC%EB%A5%B4%EB%A5%B4"
 */
@JsonClass(generateAdapter = true)
data class KakaoWebSearchData(
    @Json(name = "contents") val contents: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String
)