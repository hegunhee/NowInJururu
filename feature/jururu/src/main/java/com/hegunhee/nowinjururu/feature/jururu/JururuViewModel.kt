package com.hegunhee.nowinjururu.feature.jururu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.usecase.GetKakaoSearchPagingDataUseCase
import com.hegunhee.domain.usecase.GetStreamDataUseCase
import com.hegunhee.nowinjururu.core.navigation.twitch.TwitchDeepLink
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamActionHandler
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerViewType
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOnlineStreamer
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOfflineStreamer
import com.hegunhee.nowinjururu.feature.searchkakao.KakaoSearchActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(
    private val getStreamDataUseCase: GetStreamDataUseCase,
    private val getKakaoSearchPagingDataUseCase: GetKakaoSearchPagingDataUseCase
) : ViewModel(), StreamActionHandler, KakaoSearchActionHandler {

    private val _favoriteStreamData : MutableStateFlow<StreamerViewType> = MutableStateFlow(StreamerViewType.OfflineEmpty)
    val favoriteStreamData : StateFlow<StreamerViewType> = _favoriteStreamData.asStateFlow()

    private val _navigateTwitchDeepLink : MutableSharedFlow<TwitchDeepLink> = MutableSharedFlow()
    val navigateTwitchDeepLink : SharedFlow<TwitchDeepLink> = _navigateTwitchDeepLink.asSharedFlow()

    private var _kakaoSearchData : Flow<PagingData<KakaoSearchData>> = emptyFlow()
    val kakaoSearchData : Flow<PagingData<KakaoSearchData>>
        get() = _kakaoSearchData

    private val _navigateDeepLink : MutableSharedFlow<String> = MutableSharedFlow()
    val navigateDeepLink : SharedFlow<String> = _navigateDeepLink.asSharedFlow()

    init {
        getStreamData()
        getWebSearchData()
    }

    private fun getStreamData() {
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

    private fun getWebSearchData() {
        viewModelScope.launch {
            _kakaoSearchData = getKakaoSearchPagingDataUseCase("주르르",KakaoSearchSortType.recency,null,30).cachedIn(viewModelScope)
        }
    }

    override fun onTwitchStreamerItemClick(streamerId: String) {
        viewModelScope.launch {
            _navigateTwitchDeepLink.emit(TwitchDeepLink.Streamer(streamerId = streamerId))
        }
    }

    override fun onMoreMenuButtonClick(streamerId: String) {

    }

    override fun onSearchItemClick(url: String) {
        viewModelScope.launch {
            _navigateDeepLink.emit(url)
        }
    }
}