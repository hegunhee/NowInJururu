package com.hegunhee.data.data.json.twitch

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *"broadcaster_language": "ko",
 "broadcaster_login": "cotton__123",
 "display_name": "주르르",
 "game_id": "213930085",
 "game_name": "Honkai: Star Rail",
 "id": "203667951",
 "is_live": false,
 "tag_ids": [],
 "tags": ["브이튜버","이세계아이돌","한국어"],
"thumbnail_url": "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-300x300.png",
"title": "8시) 르르땅과 붕괴 스타레일 ~!!!!! (광고)",
"started_at": ""
 */
@JsonClass(generateAdapter = true)
data class SearchApiData(
    @Json(name = "broadcaster_language") val broadcasterLanguage: String,
    @Json(name = "broadcaster_login") val streamerId: String,
    @Json(name = "display_name") val streamerName: String,
    @Json(name = "game_id") val notUseGameId: String,
    @Json(name = "game_name") val gameName: String,
    @Json(name = "id")val id: String,
    @Json(name = "is_live")val isLive: Boolean,
    @Json(name = "started_at")val startedAt: String = "",
    @Json(name = "tag_ids")val deprecatedTags: List<Any> = emptyList(),
    @Json(name = "tags")val tags: List<String> = emptyList(),
    @Json(name = "thumbnail_url")val profileUrl: String,
    @Json(name = "title")val title: String
)