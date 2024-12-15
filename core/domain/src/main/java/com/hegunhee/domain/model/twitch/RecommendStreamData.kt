package com.hegunhee.domain.model.twitch

import com.hegunhee.domain.model.platform.StreamPlatform

data class RecommendStreamData(
    val gameId: String,
    val platform: StreamPlatform,
    val streams: List<StreamDataType.OnlineData>
)