package com.hegunhee.feature.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.usecase.GetBookmarkedStreamDataListUseCase
import com.hegunhee.domain.usecase.GetGameStreamDataListUseCase
import com.hegunhee.feature.common.twitch.TwitchDeepLink
import com.hegunhee.feature.streamer.recommend.RecommendActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamerViewModel @Inject constructor(
    private val getBookmarkedStreamDataListUseCase: GetBookmarkedStreamDataListUseCase,
    private val getGameStreamDataListUseCase: GetGameStreamDataListUseCase
) : ViewModel(), StreamActionHandler, RecommendActionHandler {

    private val _streamDataList : MutableStateFlow<List<StreamerViewType>> = MutableStateFlow(emptyList())
    val streamDataList : StateFlow<List<StreamerViewType>> = _streamDataList.asStateFlow()

    private val _recommendStreamDataList : MutableStateFlow<List<StreamerViewType.OnlineStreamer>> = MutableStateFlow(emptyList())
    val recommendStreamDataList : StateFlow<List<StreamerViewType.OnlineStreamer>> = _recommendStreamDataList.asStateFlow()

    private val _navigateTwitchDeepLink : MutableSharedFlow<TwitchDeepLink> = MutableSharedFlow()
    val navigateTwitchDeepLink : SharedFlow<TwitchDeepLink> = _navigateTwitchDeepLink.asSharedFlow()

    private val _showMoreBottomSheetDialog : MutableSharedFlow<String> = MutableSharedFlow()
    val showMoreBottomSheetDialog : SharedFlow<String> = _showMoreBottomSheetDialog.asSharedFlow()

    fun fetchBookmarkedStreamData() = viewModelScope.launch{
        getBookmarkedStreamDataListUseCase()
            .onSuccess { streamDataList ->
                _streamDataList.emit(streamDataList.toStreamViewTypeData())
                fetchRecommandGameStreamData(streamDataList.filterIsInstance<StreamDataType.OnlineData>())
            }.onFailure {

            }
    }

    private suspend fun fetchRecommandGameStreamData(onlineStreamList : List<StreamDataType.OnlineData>) {
        if(onlineStreamList.isNotEmpty()){
            val gameId = onlineStreamList.map { it.gameId }.groupBy { it }.maxBy { it.value.size }.key
            getGameStreamDataListUseCase(gameId)
                .onSuccess {
                    _recommendStreamDataList.emit(it.map { it.toOnlineStreamer() })
                }.onFailure {
                    
                }
        }
    }

    override fun onClickTwitchStreamerItem(streamerId: String) {
        viewModelScope.launch {
            _navigateTwitchDeepLink.emit(TwitchDeepLink.Streamer(streamerId = streamerId))
        }
    }

    override fun onClickMoreMenuButton(streamerId: String) {
        viewModelScope.launch {
            _showMoreBottomSheetDialog.emit(streamerId)

        }
    }

    override fun onClickGameDeepLink(gameName: String) {
        viewModelScope.launch {
            _navigateTwitchDeepLink.emit(TwitchDeepLink.Game(gameName = gameName))
        }
    }
}