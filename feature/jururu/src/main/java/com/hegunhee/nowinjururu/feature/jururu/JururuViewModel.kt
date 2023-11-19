package com.hegunhee.nowinjururu.feature.jururu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hegunhee.domain.model.kakao.KakaoFilter
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchFilterContainer
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.usecase.GetKakaoSearchPagingDataUseCase
import com.hegunhee.domain.usecase.GetStreamDataUseCase
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamActionHandler
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerViewType
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOnlineStreamer
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOfflineStreamer
import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink
import com.hegunhee.nowinjururu.core.navigation.deeplink.TwitchDeepLinkQuery
import com.hegunhee.nowinjururu.feature.searchkakao.KakaoSearchActionHandler
import com.hegunhee.nowinjururu.feature.searchkakao.filterHandler.KakaoSearchFilterActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(
    private val getStreamDataUseCase: GetStreamDataUseCase,
    private val getKakaoSearchPagingDataUseCase: GetKakaoSearchPagingDataUseCase
) : ViewModel(), StreamActionHandler, KakaoSearchActionHandler, KakaoSearchFilterActionHandler {

    private val _favoriteStreamData : MutableStateFlow<StreamerViewType> = MutableStateFlow(StreamerViewType.OfflineEmpty)
    val favoriteStreamData : StateFlow<StreamerViewType> = _favoriteStreamData.asStateFlow()

    private val _navigateDeepLink : MutableSharedFlow<DeepLink> = MutableSharedFlow()
    val navigateDeepLink : SharedFlow<DeepLink> = _navigateDeepLink.asSharedFlow()

    private var _kakaoSearchData : Flow<PagingData<KakaoSearchData>> = emptyFlow()
    val kakaoSearchData : Flow<PagingData<KakaoSearchData>>
        get() = _kakaoSearchData

    private val _kakaoSearchFilter : MutableStateFlow<KakaoSearchFilterContainer> = MutableStateFlow(KakaoSearchFilterContainer.DEFAULT)
    val kakaoSearchFilter : StateFlow<KakaoSearchFilterContainer> = _kakaoSearchFilter.asStateFlow()

    private val _navigateKakaoFilter : MutableSharedFlow<KakaoFilter> = MutableSharedFlow()
    val navigateKakaoFilter : SharedFlow<KakaoFilter> = _navigateKakaoFilter.asSharedFlow()

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
            _kakaoSearchData = getKakaoSearchPagingDataUseCase("주르르",kakaoSearchFilter.value.sortType,kakaoSearchFilter.value.searchType,30).cachedIn(viewModelScope)
        }
    }

    fun setSearchType(kakaoSearchType: KakaoSearchType) {
        _kakaoSearchFilter.value = kakaoSearchFilter.value.setSearchType(kakaoSearchType)
        getWebSearchData()
    }
    fun setSortType(kakaoSearchSortType: KakaoSearchSortType) {
        _kakaoSearchFilter.value = kakaoSearchFilter.value.setSortType(kakaoSearchSortType)
        getWebSearchData()
    }



    override fun onTwitchStreamerItemClick(streamerId: String) {
        viewModelScope.launch {

            _navigateDeepLink.emit(DeepLink.Twitch(TwitchDeepLinkQuery.Streamer(streamerId)))
        }
    }

    override fun onMoreMenuButtonClick(streamerId: String) {

    }

    override fun onSearchItemClick(url: String) {
        viewModelScope.launch {
            _navigateDeepLink.emit(DeepLink.Kakao(url = url))
        }
    }

    override fun onShareButtonClick(url: String, title: String) {
        viewModelScope.launch {
            _navigateDeepLink.emit(DeepLink.Share(baseUrl = url,title = title))
        }
    }

    override fun onClickFilterContainerButton(kakaoSearchFilter: KakaoFilter) {
        viewModelScope.launch {
            _navigateKakaoFilter.emit(kakaoSearchFilter)
        }
    }
}