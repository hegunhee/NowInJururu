package com.hegunhee.feature.streamer

interface StreamActionHandler {

    fun onClickTwitchStreamerItem(streamerLogin : String)

    fun onClickMoreMenuButton(streamerLogin : String)
}