package com.hegunhee.compose.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.compose.streamer.StreamerUiState.Error
import com.hegunhee.compose.streamer.StreamerUiState.Success
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

    private val _uiState : MutableStateFlow<StreamerUiState> = MutableStateFlow(StreamerUiState.Loading)
    val uiState : StateFlow<StreamerUiState> = _uiState.asStateFlow()

    private fun fetchStreamerData() = viewModelScope.launch {
        getBookmarkedStreamDataListUseCase()
            .onSuccess { streamDataList ->
                val onlineStreamItem = streamDataList.filterMapOnlineStreamItems()
                val offlineStreamItem = streamDataList.filterMapOfflineStreamItems()

                if(onlineStreamItem.items.isEmpty()) {
                    _uiState.value = Success(listOf(onlineStreamItem,offlineStreamItem, emptyRecommendStreamItem()))
                    return@launch
                }

                val mostFollowGameId = onlineStreamItem.items.getMostFollowedGameId()

                getGameStreamDataListUseCase(mostFollowGameId)
                    .onSuccess { recommendStreamDataList ->
                        val recommendStreamItem = recommendStreamDataList.toRecommendStreamItem(mostFollowGameId)
                        _uiState.value = Success(listOf(onlineStreamItem, offlineStreamItem, recommendStreamItem))
                    }.onFailure {
                        _uiState.value = Error(it)
                    }

            }.onFailure {
                _uiState.value = Error(it)
            }
    }

    fun onUnfollowStreamerClick(streamerId : String) {
        viewModelScope.launch {
            deleteStreamerDataUseCase(StreamerData(streamerId))
                .onSuccess {
                    fetchStreamerData()
                }.onFailure {
                    _uiState.value = Error(it)
                }
        }
    }

    fun request() {
        fetchStreamerData()
    }

}
