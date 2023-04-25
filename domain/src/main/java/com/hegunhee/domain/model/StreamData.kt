package com.hegunhee.domain.model

/**
 * EmptyData에는 Streamer Info 정보들이 담길예정
 */

sealed class StreamDataType() {
    data class StreamData(
        val gameId: String,
        val gameName: String,
        val id: String,
        val isMature: Boolean,
        val language: String,
        val startedAt: String,
        val tags: List<String> = emptyList(),
        val thumbnailUrl: String,
        val title: String,
        val type: String,
        val userId: String,
        val userLogin: String,
        val userName: String,
        val viewerCount: Int
    ) : StreamDataType()

    data class EmptyData(val userLogin : String,val userName : String,val profileUrl : String) : StreamDataType()

    companion object {
        val TestJururuUnLiveStreamInfo = StreamDataType.EmptyData(userLogin = "cotton__123", userName = "주르르",profileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-70x70.png")
    }
}
