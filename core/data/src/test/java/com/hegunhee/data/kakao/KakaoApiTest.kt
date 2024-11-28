package com.hegunhee.data.kakao

import com.hegunhee.data.getKakaoRetrofit
import com.hegunhee.data.getKakaoService
import com.hegunhee.data.getMoshi
import com.hegunhee.data.network.KakaoService
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class KakaoApiTest {

    private lateinit var sut : KakaoService
    private val query = "주르르"

    @Before
    fun initKakaoService() {
        sut = getKakaoRetrofit(getMoshi()).getKakaoService()
    }

    @Test
    fun givenSearchQuery_whenGetSearchWebData_thenReturnSearchWebDataContainQuery() = runBlocking {
        // Given
        val sortType = KakaoSearchSortType.Accuracy.sort

        // When
        val searchWebData = sut.getKakaoSearchWeb(query = query, sort = sortType, page = null, size = null).kakaoWebSearchData

        // Then
        assertTrue(searchWebData.all { it.title.contains(query) || it.contents.contains(query) })
    }

    @Test
    fun givenSearchQuery_whenGetSearchImageData_thenReturnImageUrls() = runBlocking {
        // Given
        val sortType = KakaoSearchSortType.Accuracy.sort

        // When
        val searchImageData = sut.getKakaoSearchImage(query = query, sort = sortType, page = null, size = null).kakaoImageSearchData

        // Then
        assertTrue(searchImageData.all { it.imageUrl.toHttpUrlOrNull() != null })
    }

    @Test
    fun givenSearchQuery_whenGetSearchVideoData_thenReturnSearchVideoDataContainQuery() = runBlocking {
        // Given
        val sortType = KakaoSearchSortType.Accuracy.sort

        // When
        val searchVideoData = sut.getKakaoSearchVideo(query = query, sort = sortType, page = null, size = null).kakaoVideoSearchData

        // Then
        assertTrue(searchVideoData.all { it.title.contains(query) || it.author.contains(query) })
    }

    @Test
    fun givenSortTypeRecency_whenGetSearchInfo_thenReturnDateDescending() = runBlocking {
        // Given
        val sortType = KakaoSearchSortType.Recency.sort

        // When
        val searchWebData = sut.getKakaoSearchWeb(query = query, sort = sortType, page = null, size = null).kakaoWebSearchData
        val sortSearchWebDataByDate = searchWebData.sortedByDescending { it.datetime }

        // Then
        assertEquals(searchWebData,sortSearchWebDataByDate)
        assertTrue(searchWebData.first().datetime >= searchWebData.last().datetime)
    }

}
