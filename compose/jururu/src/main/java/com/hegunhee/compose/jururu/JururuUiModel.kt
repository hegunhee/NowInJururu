package com.hegunhee.compose.jururu

import com.hegunhee.domain.model.StreamDataType

sealed class JururuUiModel {

    object Loading : JururuUiModel()

    data class Success(
        val onlineStreamData : List<StreamDataType.OnlineData>,
        val offlineStreamData : List<StreamDataType.OfflineData>
    ) : JururuUiModel()

    object Error : JururuUiModel()

}