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

    data class EmptyData(val userLogin : String,val userName : String,val profileUrl : String) : StreamDataType(
    ) {
        override fun toString(): String {
            return "EmptyData"
        }
    }
}
