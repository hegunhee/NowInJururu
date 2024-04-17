package com.hegunhee.compose.search


sealed class SearchUiModel {

    object Loading : SearchUiModel()

    object Success : SearchUiModel()

    object Error : SearchUiModel()
}