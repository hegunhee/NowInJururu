package com.hegunhee.compose.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.twitch.StreamDataType
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.usecase.twitch.DeleteStreamerDataUseCase
import com.hegunhee.domain.usecase.twitch.GetBookmarkedStreamDataListUseCase
import com.hegunhee.domain.usecase.twitch.GetGameStreamDataListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamerViewModel @Inject constructor(
    private val getBookmarkedStreamDataListUseCase: GetBookmarkedStreamDataListUseCase,
    private val getGameStreamDataListUseCase: GetGameStreamDataListUseCase,
    private val deleteStreamerDataUseCase: DeleteStreamerDataUseCase
) : ViewModel(){

    init {
        fetchStreamerData()
    }

    private val _uiModel : MutableStateFlow<StreamerUiState> = MutableStateFlow(StreamerUiState.Loading)
    val uiModel : StateFlow<StreamerUiState> = _uiModel.asStateFlow()

    private fun fetchStreamerData() = viewModelScope.launch {
        getBookmarkedStreamDataListUseCase()
            .onSuccess { streamDataList ->
                val onlineStreamItem = streamDataList.filterIsInstance<StreamDataType.OnlineData>().toOnlineStreamItem()
                val offlineStreamItem = streamDataList.filterIsInstance<StreamDataType.OfflineData>().toOfflineStreamItem()
                if(onlineStreamItem.items.isNotEmpty()) {
                    val mostFollowGameId = onlineStreamItem.items.getMostFollowedGameId()
                    getGameStreamDataListUseCase(mostFollowGameId)
                        .onSuccess { recommendStreamDataList ->
                            val recommendStreamItem = recommendStreamDataList.toRecommendStreamItem(recommendStreamDataList[0].gameName)
                            _uiModel.value = StreamerUiState.Success(listOf(onlineStreamItem, offlineStreamItem, recommendStreamItem))
                        }.onFailure {
                            _uiModel.value = StreamerUiState.Error
                        }
                }else{
                    _uiModel.value = StreamerUiState.Success(listOf(onlineStreamItem, offlineStreamItem, emptyRecommendStreamItem()))
                }
            }.onFailure {
                _uiModel.value = StreamerUiState.Error
            }
    }

    private fun List<StreamDataType.OnlineData>.getMostFollowedGameId() : String {
        return this.map {it.gameId}.groupBy { it }.maxBy { it.value.size }.key
    }

    fun onUnfollowStreamerClick(streamerId : String) {
        viewModelScope.launch {
            deleteStreamerDataUseCase(StreamerData(streamerId))
                .onSuccess {
                    fetchStreamerData()
                }.onFailure {
                    _uiModel.value = StreamerUiState.Error
                }
        }
    }

    fun request() {
        fetchStreamerData()
    }

}