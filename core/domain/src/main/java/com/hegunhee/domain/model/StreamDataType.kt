package com.hegunhee.domain.model

/**
 * EmptyData에는 Streamer Info 정보들이 담길예정
 */

sealed class StreamDataType() {
    data class OnlineData(
        val gameId: String,
        val gameName: String,
        val id: String,
        val isMature: Boolean,
        val language: String,
        val startedAt: String,
        val tags: List<String> = emptyList(),
        val thumbnailUrl: String,
        val profileUrl : String,
        val title: String,
        val type: String,
        val userId: String,
        val streamerId: String,
        val streamerName: String,
        val viewerCount: String
    ) : StreamDataType()

    data class OfflineData(val streamerId : String, val streamerName : String, val profileUrl : String) : StreamDataType()

    companion object {
        val TestJururuProfileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-70x70.png"
        val TestJururuLiveStreamInfo = OnlineData("123123","발로란트","123123",false,"ko","", listOf("게임","뭐시깽이","뭐시깽이"),"https://static-cdn.jtvnw.net/previews-ttv/live_user_hangyeol8008-300x300.jpg%22",TestJururuProfileUrl,"콘르르","live","123213123","cotton__123","주르르","1231")
        val TestJururuUnLiveStreamInfo = OfflineData(streamerId = "cotton__123", streamerName = "주르르",profileUrl = this.TestJururuProfileUrl)

        val RecommendStreamThumbNailWidth = 250
        val RecommendStreamThumbNailHeight = 200

    }
}
