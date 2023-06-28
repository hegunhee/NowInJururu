package com.hegunhee.feature.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.model.StreamerData
import com.hegunhee.domain.usecase.GetBookmarkedStreamDataListUseCase
import com.hegunhee.domain.usecase.GetGameStreamDataListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamerViewModel @Inject constructor(
    private val getBookmarkedStreamDataListUseCase: GetBookmarkedStreamDataListUseCase,
    private val getGameStreamDataListUseCase: GetGameStreamDataListUseCase
) : ViewModel(), StreamActionHandler {

    private val _streamDataList : MutableStateFlow<List<StreamerViewType>> = MutableStateFlow(emptyList())
    val streamDataList : StateFlow<List<StreamerViewType>> = _streamDataList.asStateFlow()

    private val _gameStreamDataList : MutableStateFlow<List<StreamerViewType.OnlineStreamer>> = MutableStateFlow(emptyList())
    val gameStreamDataList : StateFlow<List<StreamerViewType.OnlineStreamer>> = _gameStreamDataList.asStateFlow()

    private val _navigateStreamerTwitch : MutableSharedFlow<String> = MutableSharedFlow()
    val navigateStreamerTwitch : SharedFlow<String> = _navigateStreamerTwitch.asSharedFlow()

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
                    _gameStreamDataList.emit(it.map { it.toOnlineStreamer() })
                }.onFailure {
                    
                }
        }
    }

    override fun onClickTwitchStreamerItem(streamerLogin: String) {
        viewModelScope.launch {
            _navigateStreamerTwitch.emit(streamerLogin)
        }
    }

    override fun onClickMoreMenuButton(streamerLogin: String) {
        viewModelScope.launch {
            _showMoreBottomSheetDialog.emit(streamerLogin)

        }
    }
}