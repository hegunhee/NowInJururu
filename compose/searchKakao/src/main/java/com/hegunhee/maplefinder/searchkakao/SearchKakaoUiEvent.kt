package com.hegunhee.maplefinder.searchkakao

import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink

sealed interface SearchKakaoUiEvent {

    data class Search(val query: String): SearchKakaoUiEvent

    object SearchTypeAccuracy : SearchKakaoUiEvent

    object SearchTypeRecency : SearchKakaoUiEvent

    data class ShareClick(val deepLink : DeepLink.Share) : SearchKakaoUiEvent

    data class WebLinkClick(val deepLink : DeepLink.Kakao) : SearchKakaoUiEvent
}
