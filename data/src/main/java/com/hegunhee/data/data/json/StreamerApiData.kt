package com.hegunhee.data.data.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
    "id": "203667951",
    "login": "cotton__123",
    "display_name": "주르르",
    "type": "",
    "broadcaster_type": "partner",
    "description": "앙 트위띠~",
    "profile_image_url": "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-300x300.png",
    "offline_image_url": "https://static-cdn.jtvnw.net/jtv_user_pictures/aea85c64-5e28-4d15-81a1-db1a7a3cc1ec-channel_offline_image-1920x1080.png",
    "view_count": 4886771,
    "created_at": "2018-03-08T09:14:34Z"
 */
@JsonClass(generateAdapter = true)
data class StreamerApiData(
    @Json(name = "id") val id: String,
    @Json(name = "login") val login: String,
    @Json(name = "type") val type: String,
    @Json(name = "display_name") val display_name: String,
    @Json(name = "broadcaster_type") val broadcaster_type: String,
    @Json(name = "description") val description: String,
    @Json(name = "profile_image_url") val profile_image_url: String,
    @Json(name = "offline_image_url") val offline_image_url: String,
    @Json(name = "view_count") val view_count: Int,
    @Json(name = "created_at") val created_at: String
)