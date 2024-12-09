package com.hegunhee.compose.streamer

sealed class StreamerUiState {

    object Loading : StreamerUiState()

    data class Success(
        val streamItem : List<StreamItem>
    ) : StreamerUiState()

    object Error : StreamerUiState()
}
