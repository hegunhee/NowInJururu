package com.hegunhee.feature.search

sealed interface SearchUiEvent {

    object Refresh : SearchUiEvent

}