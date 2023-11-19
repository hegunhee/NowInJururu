package com.hegunhee.data.dataSource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hegunhee.data.mapper.toModel
import com.hegunhee.data.network.KakaoService
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.model.kakao.KakaoSearchType.*
import retrofit2.HttpException
import java.io.IOException

class KakaoSearchPagingSource(
    private val query : String,
    private val kakaoService : KakaoService,
    private val sortType : String,
    private val searchType : KakaoSearchType,
) : PagingSource<Int, KakaoSearchData>(){

    private val searchTypeSize = 3

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, KakaoSearchData> {
        val page = params.key ?: 1
        return try {
            val (searchData, isEnd) = getServiceResponse(page,params.loadSize)
            LoadResult.Page(
                data = searchData,
                prevKey = if (page == 1) null else page -1,
                nextKey = if (isEnd) null else page + 1
            )
        }catch (exception : IOException) {
            return LoadResult.Error(exception)
        } catch (exception : HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, KakaoSearchData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private suspend fun getServiceResponse(page : Int,size : Int) : Pair<List<KakaoSearchData>,Boolean> {
        when(searchType) {
            Web -> {
                val response = kakaoService.getKakaoSearchWeb(query,sortType,page,size)
                val isEnd = response.meta.isEnd
                return Pair(response.kakaoWebSearchData.map { it.toModel() },isEnd)
            }
            Image -> {
                val response = kakaoService.getKakaoSearchImage(query,sortType,page,size)
                val isEnd = response.meta.isEnd
                return Pair(response.kakaoImageSearchData.map {it.toModel()},isEnd)
            }
            Video -> {
                val response = kakaoService.getKakaoSearchImage(query,sortType,page,size)
                val isEnd = response.meta.isEnd
                return Pair(response.kakaoImageSearchData.map {it.toModel()},isEnd)
            }
            Default -> {
                val (webData, webMeta) = kakaoService.getKakaoSearchWeb(query,sortType,page,size/searchTypeSize)
                val (imageData, imageMeta) = kakaoService.getKakaoSearchImage(query,sortType,page,size/searchTypeSize)
                val (videoData, videoMeta) = kakaoService.getKakaoSearchVideo(query,sortType,page,size/searchTypeSize)
                val searchData = webData.map { it.toModel() } + imageData.map { it.toModel() } + videoData.map { it.toModel() }
                val isEnd = webMeta.isEnd || imageMeta.isEnd || videoMeta.isEnd
                return Pair(searchData,isEnd)
            }
        }
    }


}