package com.hegunhee.compose.streamer

sealed class StreamerUiModel {

    object Loading : StreamerUiModel()

    data class Success(
        val streamItem : List<StreamItem>
    ) : StreamerUiModel()

    object Error : StreamerUiModel()
}