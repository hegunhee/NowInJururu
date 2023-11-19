package com.hegunhee.domain.model.kakao

data class KakaoSearchFilterContainer private constructor(
    val searchType : KakaoSearchType,
    val sortType : KakaoSearchSortType
) {

    fun setSearchType(searchType: KakaoSearchType) : KakaoSearchFilterContainer{
        return KakaoSearchFilterContainer(searchType,this.sortType)
    }

    fun setSortType(sortType : KakaoSearchSortType) : KakaoSearchFilterContainer {
        return KakaoSearchFilterContainer(this.searchType,sortType)
    }

    companion object{
        val DEFAULT = KakaoSearchFilterContainer(searchType = KakaoSearchType.Default, sortType = KakaoSearchSortType.Recency)
    }
}