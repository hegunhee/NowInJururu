package com.hegunhee.feature.streamer.jururu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.usecase.GetJururuStreamDataUseCase
import com.hegunhee.feature.common.twitch.TwitchDeepLink
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamActionHandler
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerViewType
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOnlineStreamer
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOfflineStreamer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(private val getJururuStreamDataUseCase: GetJururuStreamDataUseCase) : ViewModel(),
    StreamActionHandler {

    private val _jururuStreamData : MutableStateFlow<StreamerViewType> = MutableStateFlow(
        StreamerViewType.OfflineStreamer("","",""))
    val jururuStreamData : StateFlow<StreamerViewType> = _jururuStreamData.asStateFlow()

    private val _navigateTwitchDeepLink : MutableSharedFlow<TwitchDeepLink> = MutableSharedFlow()
    val navigateTwitchDeepLink : SharedFlow<TwitchDeepLink> = _navigateTwitchDeepLink.asSharedFlow()

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

    override fun onTwitchStreamerItemClick(streamerId: String) {
        viewModelScope.launch {
            _navigateTwitchDeepLink.emit(TwitchDeepLink.Streamer(streamerId = streamerId))
        }
    }

    override fun onMoreMenuButtonClick(streamerId: String) {

    }
}