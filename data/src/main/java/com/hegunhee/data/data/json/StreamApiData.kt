package com.hegunhee.data.data.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *  "id": "40398491671",
    "user_id": "856575550",
    "user_login": "ayatsunoyuni_stellive",
    "user_name": "아야츠노_유니",
    "game_id": "516575",
    "game_name": "VALORANT",
    "type": "live",
    "title": "워치파티 !!!!",
    "viewer_count": 8064,
    "started_at": "2023-06-25T03:36:17Z",
    "language": "ko",
    "thumbnail_url": "https://static-cdn.jtvnw.net/previews-ttv/live_user_ayatsunoyuni_stellive-{width}x{height}.jpg",
    "tag_ids": [],
    "tags": [
        "브이튜버",
        "스텔라이브",
        "유니",
        "아야츠노유니",
        "한국어"
    ],
    "is_mature": false
 */
@JsonClass(generateAdapter = true)
data class StreamApiData(
    @Json(name = "game_id")val gameId: String,
    @Json(name = "game_name")val gameName: String,
    @Json(name = "id")val id: String,
    @Json(name = "is_mature")val isMature: Boolean,
    @Json(name = "language")val language: String,
    @Json(name = "started_at")val startedAt: String,
    @Json(name = "tag_ids")val deprecatedTagIds: List<Any> = emptyList(),
    @Json(name = "tags")val tags: List<String> = emptyList(),
    @Json(name = "thumbnail_url")val thumbnailUrl: String,
    @Json(name = "title")val title: String,
    @Json(name = "type")val type: String,
    @Json(name = "user_id")val userId: String,
    @Json(name = "user_login")val streamerId: String,
    @Json(name = "user_name")val streamerName: String,
    @Json(name = "viewer_count")val viewerCount: Int
)