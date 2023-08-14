package com.hegunhee.compose.jururu

import com.hegunhee.domain.model.StreamDataType

sealed class JururuUiState {

    object Loading : JururuUiState()

    data class Success(val streamerList : List<StreamDataType>) : JururuUiState()

    object Error : JururuUiState()

}