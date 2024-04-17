package com.hegunhee.feature.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.usecase.twitch.GetBookmarkedStreamDataListUseCase
import com.hegunhee.domain.usecase.twitch.GetGameStreamDataListUseCase
import com.hegunhee.nowinjururu.core.designsystem.adapter.recommend.RecommendActionHandler
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamActionHandler
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerViewType
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toOnlineStreamer
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.toStreamViewTypeData
import com.hegunhee.nowinjururu.core.navigation.deeplink.DeepLink
import com.hegunhee.nowinjururu.core.navigation.deeplink.TwitchDeepLinkQuery
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

    private val _navigateDeepLink : MutableSharedFlow<DeepLink> = MutableSharedFlow()
    val navigateDeepLink : SharedFlow<DeepLink> = _navigateDeepLink.asSharedFlow()

    private val _showMoreBottomSheetDialog : MutableSharedFlow<String> = MutableSharedFlow()
    val showMoreBottomSheetDialog : SharedFlow<String> = _showMoreBottomSheetDialog.asSharedFlow()

    private val _navigateDetailStreamer : MutableSharedFlow<String> = MutableSharedFlow()
    val navigateDetailStreamer : SharedFlow<String> = _navigateDetailStreamer.asSharedFlow()

    init {
        fetchBookmarkedStreamData()
    }

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

    override fun onTwitchStreamerItemClick(streamerId: String) {
        viewModelScope.launch {
            _navigateDetailStreamer.emit(streamerId)
        }
    }

    override fun onMoreMenuButtonClick(streamerId: String) {
        viewModelScope.launch {
            _showMoreBottomSheetDialog.emit(streamerId)

        }
    }

    override fun onGameDeepLinkClick(gameName: String) {
        viewModelScope.launch {
            _navigateDeepLink.emit(DeepLink.Twitch(TwitchDeepLinkQuery.Game(gameName)))
        }
    }
}