package com.hegunhee.feature.streamer.jururu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.usecase.GetJururuStreamDataUseCase
import com.hegunhee.feature.streamer.StreamActionHandler
import com.hegunhee.feature.streamer.StreamerViewType
import com.hegunhee.feature.streamer.toOnlineStreamer
import com.hegunhee.feature.streamer.toOfflineStreamer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(private val getJururuStreamDataUseCase: GetJururuStreamDataUseCase) : ViewModel(), StreamActionHandler {

    private val _jururuStreamData : MutableStateFlow<StreamerViewType> = MutableStateFlow(StreamerViewType.OfflineStreamer("","",""))
    val jururuStreamData : StateFlow<StreamerViewType> = _jururuStreamData.asStateFlow()

    private val _navigateStreamerTwitch : MutableSharedFlow<String> = MutableSharedFlow()
    val navigateStreamerTwitch : SharedFlow<String> = _navigateStreamerTwitch.asSharedFlow()

    fun getJururuStreamData() {
        viewModelScope.launch {
            getJururuStreamDataUseCase()
                .onSuccess {
                    if(it is StreamDataType.OnlineData) {
                        _jururuStreamData.emit(it.toOnlineStreamer())
                    }else if(it is StreamDataType.OfflineData){
                        _jururuStreamData.emit(it.toOfflineStreamer())
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