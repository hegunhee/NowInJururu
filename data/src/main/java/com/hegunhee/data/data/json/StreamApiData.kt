package com.hegunhee.data.data.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
    @Json(name = "user_login")val userLogin: String,
    @Json(name = "user_name")val userName: String,
    @Json(name = "viewer_count")val viewerCount: Int
)