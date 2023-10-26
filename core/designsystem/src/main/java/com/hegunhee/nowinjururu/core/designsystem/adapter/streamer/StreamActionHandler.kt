package com.hegunhee.nowinjururu.core.designsystem.adapter.streamer

interface StreamActionHandler {

    fun onClickTwitchStreamerItem(streamerId : String)

    fun onClickMoreMenuButton(streamerId : String)
}