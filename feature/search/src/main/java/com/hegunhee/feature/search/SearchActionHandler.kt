package com.hegunhee.feature.search

interface SearchActionHandler {

    fun onClickStreamerItem(streamerId : String)

    fun onClickBookMarkStreamer(streamerId : String)
}