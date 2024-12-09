package com.hegunhee.compose.streamer

sealed class StreamerUiState {

    object Loading : StreamerUiState()

    data class Success(
        val streamItem: List<StreamItem>
    ) : StreamerUiState()

    data class Error(val exception: Throwable) : StreamerUiState()
}
