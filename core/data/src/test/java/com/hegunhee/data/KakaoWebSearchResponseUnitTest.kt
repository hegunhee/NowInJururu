package com.hegunhee.data

import com.hegunhee.data.network.KakaoService
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
}