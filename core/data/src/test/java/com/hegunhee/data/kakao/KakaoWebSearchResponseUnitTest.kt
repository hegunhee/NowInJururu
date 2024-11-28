package com.hegunhee.data.kakao

import com.hegunhee.data.getKakaoRetrofit
import com.hegunhee.data.getKakaoService
import com.hegunhee.data.getMoshi
import com.hegunhee.data.mapper.toModel
import com.hegunhee.data.network.KakaoService
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class KakaoWebSearchResponseUnitTest {

    private lateinit var kakaoService : KakaoService

    @Before
    fun initKakaoService() {
        val moshi = getMoshi()
        kakaoService = getKakaoRetrofit(moshi).getKakaoService()
    }

    @Test
    fun getKakaoWebResult() {
        runBlocking {
            runCatching {
                kakaoService.getKakaoSearchWeb(
                    query = "주르르",
                    sort = "recency",
                    page = null,
                    size = null
                )
            }.onSuccess {
                println(it.kakaoWebSearchData)
                assert(true)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get web result to domain data`() {
        runBlocking {
            kotlin.runCatching {
                kakaoService.getKakaoSearchWeb(
                    query = "주르르",
                    sort = KakaoSearchSortType.TYPE,
                    page = null,
                    size = null
                ).kakaoWebSearchData.map { it.toModel() }
            }.onSuccess {
                println(it)
                assert(true)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}