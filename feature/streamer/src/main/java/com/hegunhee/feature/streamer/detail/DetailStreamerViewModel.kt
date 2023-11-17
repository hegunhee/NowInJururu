package com.hegunhee.feature.streamer.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.usecase.GetKakaoSearchPagingDataUseCase
import com.hegunhee.domain.usecase.GetSearchStreamerDataUseCase
import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink
import com.hegunhee.nowinjururu.core.navigation.deeplink.TwitchDeepLinkQuery
import com.hegunhee.nowinjururu.feature.searchkakao.KakaoSearchActionHandler
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
) : ViewModel(), KakaoSearchActionHandler {

    private val _streamerData : MutableStateFlow<SearchData> = MutableStateFlow(SearchData.EMPTY)
    val streamerData : StateFlow<SearchData> = _streamerData.asStateFlow()

    private var _kakaoSearchData : Flow<PagingData<KakaoSearchData>> = flow { PagingData.empty<KakaoSearchData>()}
    val kakaoSearchData : Flow<PagingData<KakaoSearchData>>
        get() = _kakaoSearchData

    private val _navigationEvent : MutableSharedFlow<DetailStreamerNavigationEvent> = MutableSharedFlow()
    val navigationEvent : SharedFlow<DetailStreamerNavigationEvent> = _navigationEvent.asSharedFlow()

    private val _navigateDeepLink : MutableSharedFlow<DeepLink> = MutableSharedFlow()
    val navigateDeepLink : SharedFlow<DeepLink> = _navigateDeepLink.asSharedFlow()


    fun fetchStreamerData(streamerId : String){
        viewModelScope.launch {
            getSearchStreamerDataUseCase(streamerId)
                .onSuccess { searchData ->
                    _streamerData.value = searchData
                }.onFailure {

                }
        }
    }

    fun getWebSearchData(streamerName : String) {
        viewModelScope.launch {
            _kakaoSearchData = getKakaoSearchPagingDataUseCase(streamerName, KakaoSearchSortType.recency,null,30).cachedIn(viewModelScope)
        }
    }

    fun onProfileImageClick() {
        val streamerData = streamerData.value
        viewModelScope.launch {
            if(!streamerData.isEmpty()) {
                _navigateDeepLink.emit(DeepLink.Twitch(TwitchDeepLinkQuery.Streamer(streamerData.streamerId)))
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