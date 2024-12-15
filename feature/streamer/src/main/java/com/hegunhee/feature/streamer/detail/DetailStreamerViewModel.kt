package com.hegunhee.feature.streamer.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hegunhee.domain.model.kakao.KakaoFilter
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchFilterContainer
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.usecase.kakao.GetKakaoSearchPagingDataUseCase
import com.hegunhee.domain.usecase.twitch.GetSearchStreamerDataUseCase
import com.hegunhee.nowinjururu.core.navigation.deeplink.type.DeepLink
import com.hegunhee.nowinjururu.feature.searchkakao.KakaoSearchActionHandler
import com.hegunhee.nowinjururu.feature.searchkakao.filterHandler.KakaoSearchFilterActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailStreamerViewModel @Inject constructor(
    private val getSearchStreamerDataUseCase: GetSearchStreamerDataUseCase,
    private val getKakaoSearchPagingDataUseCase: GetKakaoSearchPagingDataUseCase
) : ViewModel(), KakaoSearchActionHandler, KakaoSearchFilterActionHandler {

    private val _streamerData : MutableStateFlow<SearchData> = MutableStateFlow(SearchData.EMPTY)
    val streamerData : StateFlow<SearchData> = _streamerData.asStateFlow()

    private var _kakaoSearchData : Flow<PagingData<KakaoSearchData>> = flow { PagingData.empty<KakaoSearchData>()}
    val kakaoSearchData : Flow<PagingData<KakaoSearchData>>
        get() = _kakaoSearchData

    private val _navigationEvent : MutableSharedFlow<DetailStreamerNavigationEvent> = MutableSharedFlow()
    val navigationEvent : SharedFlow<DetailStreamerNavigationEvent> = _navigationEvent.asSharedFlow()

    private val _navigateDeepLink : MutableSharedFlow<DeepLink> = MutableSharedFlow()
    val navigateDeepLink : SharedFlow<DeepLink> = _navigateDeepLink.asSharedFlow()

    private val _kakaoSearchFilter : MutableStateFlow<KakaoSearchFilterContainer> = MutableStateFlow(KakaoSearchFilterContainer.DEFAULT)
    val kakaoSearchFilter : StateFlow<KakaoSearchFilterContainer> = _kakaoSearchFilter.asStateFlow()


    private val _navigateKakaoFilter : MutableSharedFlow<KakaoFilter> = MutableSharedFlow()
    val navigateKakaoFilter : SharedFlow<KakaoFilter> = _navigateKakaoFilter.asSharedFlow()


    fun fetchStreamerData(streamerId : String){
        viewModelScope.launch {
            getSearchStreamerDataUseCase(streamerId)
                .onSuccess { searchData ->
                    _streamerData.value = searchData
                }.onFailure { }
        }
    }

    fun getWebSearchData(streamerName : String = streamerData.value.streamerName) {
        viewModelScope.launch {
            _kakaoSearchData = getKakaoSearchPagingDataUseCase(streamerName, kakaoSearchFilter.value.sortType,kakaoSearchFilter.value.searchType,30).cachedIn(viewModelScope)
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

    override fun onFilterContainerButtonClick(kakaoSearchFilter: KakaoFilter) {
        viewModelScope.launch {
            _navigateKakaoFilter.emit(kakaoSearchFilter)
        }
    }

    fun onProfileImageClick() {
        val streamerData = streamerData.value
        viewModelScope.launch {
            if(!streamerData.isEmpty()) {
                _navigateDeepLink.emit(DeepLink.TwitchStreamer(streamerData.streamerId))
            }
        }

    }

    fun onBackButtonClick() {
        viewModelScope.launch {
            _navigationEvent.emit(DetailStreamerNavigationEvent.Back)
        }
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
}