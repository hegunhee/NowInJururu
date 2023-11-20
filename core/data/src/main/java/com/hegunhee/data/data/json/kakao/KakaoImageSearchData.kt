package com.hegunhee.data.data.json.kakao

import com.hegunhee.domain.model.ImageUrl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * "collection": "blog",
 * "datetime": "2022-03-01T01:33:27.000+09:00",
 * "display_sitename": "티스토리",
 * "doc_url": "https://makedon.tistory.com/160",
 * "height": 463,
 * "image_url": "https://blog.kakaocdn.net/dn/cNu4gq/btruKjWYyiI/c4U1f8AJYbLWsNheK63INK/img.png",
 * "thumbnail_url": "https://search1.kakaocdn.net/argon/130x130_85_c/9A0v9BxkSxB",
 * "width": 500
 */
@JsonClass(generateAdapter = true)
data class KakaoImageSearchData(
    @Json(name = "collection") val sourceType: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "display_sitename") val displaySiteName: String,
    @Json(name = "doc_url") val url: String,
    @Json(name = "height") val height: Int,
    @Json(name = "image_url") val imageUrl: ImageUrl,
    @Json(name = "thumbnail_url") val thumbNailUrl: ImageUrl,
    @Json(name = "width") val width: Int
)