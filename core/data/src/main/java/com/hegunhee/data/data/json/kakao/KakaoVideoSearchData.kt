package com.hegunhee.data.data.json.kakao

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * "author": "봉인 풀린 주르르",
 * "datetime": "2023-10-27T15:00:31.000+09:00",
 * "play_time": 103,
 * "thumbnail": "https://search4.kakaocdn.net/argon/138x78_80_pr/7fDZAJLFEOB",
 * "title": "주르르식 낭만",
 * "url": "http://www.youtube.com/watch?v=v4xx2vFK2ts"
 */
@JsonClass(generateAdapter = true)
data class KakaoVideoSearchData(
    @Json(name = "author") val author: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "play_time") val playTime: Int,
    @Json(name = "thumbnail") val thumbNailUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String
)