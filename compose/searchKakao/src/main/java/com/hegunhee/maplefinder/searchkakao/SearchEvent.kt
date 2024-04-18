package com.hegunhee.maplefinder.searchkakao

sealed interface SearchEvent {

    object SearchAccuracy : SearchEvent

    object SearchRecency : SearchEvent
}