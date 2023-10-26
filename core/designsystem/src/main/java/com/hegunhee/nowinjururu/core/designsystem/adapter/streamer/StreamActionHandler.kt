package com.hegunhee.nowinjururu.core.designsystem.adapter.streamer

interface StreamActionHandler {

    fun onTwitchStreamerItemClick(streamerId : String)

    fun onMoreMenuButtonClick(streamerId : String)
}