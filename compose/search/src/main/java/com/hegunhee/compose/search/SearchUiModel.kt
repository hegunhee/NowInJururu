package com.hegunhee.compose.search

import com.hegunhee.domain.model.twitch.SearchData

sealed class SearchUiModel {

    object Loading : SearchUiModel()

    data class Success(val streamerList : List<SearchData>) : SearchUiModel()

    object Error : SearchUiModel()
}