package com.hegunhee.compose.search


sealed class SearchUiState {

    object Loading : SearchUiState()

    object Success : SearchUiState()

    data class Error(val exception: Throwable) : SearchUiState()
}
