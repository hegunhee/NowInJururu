package com.hegunhee.maplefinder.searchkakao

import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink

sealed interface SearchEvent {

    object SearchAccuracy : SearchEvent

    object SearchRecency : SearchEvent

    data class ShareClick(val deepLink : DeepLink.Share) : SearchEvent

    data class WebLinkClick(val deepLink : DeepLink.Kakao) : SearchEvent
}