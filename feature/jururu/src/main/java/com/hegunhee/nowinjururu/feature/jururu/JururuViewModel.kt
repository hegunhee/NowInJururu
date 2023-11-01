package com.hegunhee.nowinjururu.feature.jururu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.usecase.GetStreamDataUseCase
import com.hegunhee.domain.usecase.GetWebSearchDataListUseCase
import com.hegunhee.nowinjururu.core.navigation.twitch.TwitchDeepLink
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamActionHandler
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerViewType
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOnlineStreamer
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOfflineStreamer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(
    private val getStreamDataUseCase: GetStreamDataUseCase,
    private val getWebSearchDataListUseCase : GetWebSearchDataListUseCase
) : ViewModel(),
    StreamActionHandler {

    private val _favoriteStreamData : MutableStateFlow<StreamerViewType> = MutableStateFlow(StreamerViewType.OfflineEmpty)
    val favoriteStreamData : StateFlow<StreamerViewType> = _favoriteStreamData.asStateFlow()

    private val _navigateTwitchDeepLink : MutableSharedFlow<TwitchDeepLink> = MutableSharedFlow()
    val navigateTwitchDeepLink : SharedFlow<TwitchDeepLink> = _navigateTwitchDeepLink.asSharedFlow()

    private val _webSearchData : MutableStateFlow<List<KakaoSearchData.Web>> = MutableStateFlow(emptyList())
    val webSearchData : StateFlow<List<KakaoSearchData.Web>> = _webSearchData.asStateFlow()
    fun getStreamData() {
        viewModelScope.launch {
            getStreamDataUseCase("cotton__123")
                .onSuccess {
                    if(it is StreamDataType.OnlineData) {
                        _favoriteStreamData.value = it.toOnlineStreamer()
                    }else if(it is StreamDataType.OfflineData){
                        _favoriteStreamData.value = it.toOfflineStreamer()
                    }

                }
                .onFailure {  }
        }
    }

    fun getWebSearchData() {
        viewModelScope.launch {
            getWebSearchDataListUseCase("주르르",KakaoSearchSortType.recency)
                .onSuccess {
                    _webSearchData.value = it
                }.onFailure {

                }
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