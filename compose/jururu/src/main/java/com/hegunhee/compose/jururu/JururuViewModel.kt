package com.hegunhee.compose.jururu

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.usecase.GetStreamDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(private val getStreamDataUseCase: GetStreamDataUseCase) : ViewModel(){

    init{
        getStreamData()
    }

    private val _uiModel : MutableState<JururuUiModel> = mutableStateOf<JururuUiModel>(JururuUiModel.Loading)
    val uiModel: State<JururuUiModel>
        get() = _uiModel

    private fun getStreamData() {
        viewModelScope.launch {
            getStreamDataUseCase("cotton__123")
                .onSuccess {
                    val onlineStreamData = listOf(it).filterIsInstance<StreamDataType.OnlineData>()
                    val offlineStreamData = listOf(it).filterIsInstance<StreamDataType.OfflineData>()
                    _uiModel.value = JururuUiModel.Success(onlineStreamData, offlineStreamData)
                }.onFailure {
                    _uiModel.value = JururuUiModel.Error
                }
        }
    }

    fun onUnfollowButtonClick(streamId : String) {

    }


}