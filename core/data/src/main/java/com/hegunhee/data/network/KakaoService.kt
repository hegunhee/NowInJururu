package com.hegunhee.data.network

import com.hegunhee.data.data.json.kakao.KakaoImageSearchResponse
import com.hegunhee.data.data.json.kakao.KakaoVideoSearchResponse
import com.hegunhee.data.data.json.kakao.KakaoWebSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {

    @GET("web")
    suspend fun getKakaoSearchWeb(
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page : Int?,
        @Query("size") size : Int?
    ) : KakaoWebSearchResponse

    @GET("vclip")
    suspend fun getKakaoSearchVideo(
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page : Int?,
        @Query("size") size : Int?
    ) : KakaoVideoSearchResponse

    @GET("image")
    suspend fun getKakaoSearchImage(
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page : Int?,
        @Query("size") size : Int?
    ) : KakaoImageSearchResponse
}