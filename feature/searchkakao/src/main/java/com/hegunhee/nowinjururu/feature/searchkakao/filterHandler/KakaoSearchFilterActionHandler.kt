package com.hegunhee.nowinjururu.feature.searchkakao.filterHandler

import com.hegunhee.domain.model.kakao.KakaoFilter

interface KakaoSearchFilterActionHandler {

    fun onFilterContainerButtonClick(kakaoSearchFilter : KakaoFilter)
}