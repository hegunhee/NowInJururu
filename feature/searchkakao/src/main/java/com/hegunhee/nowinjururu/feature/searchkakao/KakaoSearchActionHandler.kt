package com.hegunhee.nowinjururu.feature.searchkakao

interface KakaoSearchActionHandler {

    fun onSearchItemClick(url: String)

    fun onShareButtonClick(url: String, title: String)
}