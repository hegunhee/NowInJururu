package com.hegunhee.domain.model.kakao

import com.hegunhee.domain.model.ImageUrl
import java.net.URL

sealed class KakaoSearchData(val type : KakaoSearchType,val time : String) {

    data class Web(
        val contents : String,
        val dateTime : String,
        val title : String,
        val url : String,
    ) : KakaoSearchData(KakaoSearchType.WEB,dateTime) {

        fun getUrlDomain() : String = URL(url).host.removePrefix("www.") ?: ""
    }
    data class Image(
        val sourceType : String,
        val dateTime : String,
        val displaySiteName : String,
        val url : String,
        val imageUrl : ImageUrl,
        val thumbNailUrl : ImageUrl,
        val width : Int,
        val height : Int
    ) : KakaoSearchData(KakaoSearchType.IMAGE,dateTime)

    data class Video(
        val author : String,
        val dateTime : String,
        val playTime : Int,
        val thumbNailUrl : ImageUrl,
        val title : String,
        val url : String
    ) : KakaoSearchData(KakaoSearchType.VIDEO,dateTime)

}

enum class KakaoSearchType {
    WEB, IMAGE,VIDEO
}
