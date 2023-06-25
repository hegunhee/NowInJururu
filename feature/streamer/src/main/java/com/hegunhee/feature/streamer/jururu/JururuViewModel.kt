package com.hegunhee.feature.streamer.jururu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.usecase.GetJururuStreamDataUseCase
import com.hegunhee.feature.streamer.StreamActionHandler
import com.hegunhee.feature.streamer.StreamerViewType
import com.hegunhee.feature.streamer.toLiveStreamer
import com.hegunhee.feature.streamer.toUnLiveStreamer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(private val getJururuStreamDataUseCase: GetJururuStreamDataUseCase) : ViewModel(), StreamActionHandler {

    private val _jururuStreamData : MutableStateFlow<StreamerViewType> = MutableStateFlow(StreamerViewType.UnLiveStreamer("","",""))
    val jururuStreamData : StateFlow<StreamerViewType> = _jururuStreamData.asStateFlow()

    private val _navigateStreamerTwitch : MutableSharedFlow<String> = MutableSharedFlow()
    val navigateStreamerTwitch : SharedFlow<String> = _navigateStreamerTwitch.asSharedFlow()

    fun getJururuStreamData() {
        viewModelScope.launch {
            getJururuStreamDataUseCase()
                .onSuccess {
                    if(it is StreamDataType.OnlineData) {
                        _jururuStreamData.emit(it.toLiveStreamer())
                    }else if(it is StreamDataType.OfflineData){
                        _jururuStreamData.emit(it.toUnLiveStreamer())
                    }

                }
                .onFailure {  }
        }
    }

    override fun onClickTwitchStreamerItem(streamerLogin: String) {
        viewModelScope.launch {
            _navigateStreamerTwitch.emit(streamerLogin)
        }
    }

    override fun onClickMoreMenuButton(streamerLogin: String) {

    }
}