package com.hegunhee.data.mapper

import com.hegunhee.data.data.json.kakao.KakaoImageSearchData
import com.hegunhee.data.data.json.kakao.KakaoVideoSearchData
import com.hegunhee.data.data.json.kakao.KakaoWebSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchData
import java.text.SimpleDateFormat

fun KakaoImageSearchData.toModel(): KakaoSearchData.Image =
    KakaoSearchData.Image(
        sourceType = sourceType,
        dateTime = datetime.transferDateTime(),
        displaySiteName = displaySiteName,
        url = url,
        imageUrl = imageUrl,
        thumbNailUrl = thumbNailUrl,
        width = width,
        height = height
    )

fun KakaoVideoSearchData.toModel() : KakaoSearchData.Video =
    KakaoSearchData.Video(
        author = author,
        dateTime = datetime.transferDateTime(),
        playTime = playTime,
        thumbNailUrl = thumbNailUrl,
        title = title,
        url = url
    )

fun KakaoWebSearchData.toModel() : KakaoSearchData.Web =
    KakaoSearchData.Web(
        contents = contents,
        dateTime = datetime.transferDateTime(),
        title = title,
        url = url
    )

private val kakaoApiResponseFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
private val featureFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm")

private fun String.transferDateTime() : String{
    val date = kakaoApiResponseFormat.parse(this)
    return featureFormat.format(date)
}