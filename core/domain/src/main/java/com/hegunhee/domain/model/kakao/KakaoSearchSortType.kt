package com.hegunhee.domain.model.kakao

sealed class KakaoSearchSortType(val name : String,val sort : String) : KakaoFilter {

    override val type: String
        get() = TYPE

    override val filterList: Array<String>
        get() = arrayOf(Accuracy.name,Recency.name)

    object Accuracy : KakaoSearchSortType(name = "정확도순",sort = "accuracy")

    object Recency : KakaoSearchSortType(name = "최신순",sort = "recency")

    companion object {
        val TYPE = "정렬순"

        fun findType(name : String) : KakaoSearchSortType {
            return when(name) {
                Recency.name -> Recency
                else -> Accuracy
            }
        }
    }

}