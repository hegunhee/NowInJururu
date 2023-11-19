package com.hegunhee.domain.model.kakao

import com.hegunhee.domain.model.ImageUrl
import java.net.URL

sealed class KakaoSearchData(val type : KakaoSearchType,val time : String) {

    data class Web(
        val contents : String,
        val dateTime : String,
        val title : String,
        val url : String,
    ) : KakaoSearchData(KakaoSearchType.Web,dateTime) {

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
    ) : KakaoSearchData(KakaoSearchType.Image,dateTime)

    data class Video(
        val author : String,
        val dateTime : String,
        val playTime : Int,
        val thumbNailUrl : ImageUrl,
        val title : String,
        val url : String
    ) : KakaoSearchData(KakaoSearchType.Video,dateTime) {

        fun getVideoInfo() : String {
            return "$author|조회수 $playTime|$dateTime"
        }
    }

}
sealed class KakaoSearchType(val name : String) : KakaoFilter {

    override val type: String
        get() = TYPE

    override val filterList : Array<String>
        get() = arrayOf<String>(Web.name,Image.name,Video.name)


    object Default : KakaoSearchType(name = "Default")

    object Web : KakaoSearchType(name = "웹")

    object Image : KakaoSearchType(name = "이미지")

    object Video : KakaoSearchType(name = "비디오")


    companion object {
        val TYPE = "검색 결과"
    }
}

interface KakaoFilter {
    val type : String
    val filterList : Array<String>
}
