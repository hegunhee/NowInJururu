package com.hegunhee.data.data.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchApiData(
    @Json(name = "broadcaster_language") val broadcasterLanguage: String,
    @Json(name = "broadcaster_login") val broadcasterLogin: String,
    @Json(name = "display_name") val displayName: String,
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