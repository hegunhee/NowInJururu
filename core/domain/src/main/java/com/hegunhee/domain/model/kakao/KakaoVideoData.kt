package com.hegunhee.domain.model.kakao

import com.hegunhee.domain.model.ImageUrl

data class KakaoVideoData(
    val author : String,
    val dateTime : String,
    val playTime : Int,
    val thumbNailUrl : ImageUrl,
    val title : String,
    val url : String
)