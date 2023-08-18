package com.hegunhee.compose.jururu

import com.hegunhee.domain.model.StreamDataType

sealed class JururuUiState {

    object Loading : JururuUiState()

    data class Success(
        val onlineStreamData : List<StreamDataType.OnlineData>,
        val offlineStreamData : List<StreamDataType.OfflineData>
    ) : JururuUiState()

    object Error : JururuUiState()

}