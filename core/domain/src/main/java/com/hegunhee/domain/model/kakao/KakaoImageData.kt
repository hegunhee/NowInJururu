package com.hegunhee.domain.model.kakao

import com.hegunhee.domain.model.ImageUrl

data class KakaoImageData(
    val sourceType : String,
    val dateTime : String,
    val displaySiteName : String,
    val url : String,
    val imageUrl : ImageUrl,
    val thumbNailUrl : ImageUrl,
    val width : Int,
    val height : Int
)