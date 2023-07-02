package com.hegunhee.feature.streamer

interface StreamActionHandler {

    fun onClickTwitchStreamerItem(streamerId : String)

    fun onClickMoreMenuButton(streamerId : String)
}