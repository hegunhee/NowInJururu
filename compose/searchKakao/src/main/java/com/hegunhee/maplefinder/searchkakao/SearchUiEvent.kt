package com.hegunhee.maplefinder.searchkakao

import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink

sealed interface SearchUiEvent {

    object SearchUiAccuracy : SearchUiEvent

    object SearchUiRecency : SearchUiEvent

    data class ShareClick(val deepLink : DeepLink.Share) : SearchUiEvent

    data class WebLinkClick(val deepLink : DeepLink.Kakao) : SearchUiEvent
}