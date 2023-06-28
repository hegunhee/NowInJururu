package com.hegunhee.feature.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
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

    private val _navigateStreamerTwitch : MutableSharedFlow<String> = MutableSharedFlow()
    val navigateStreamerTwitch : SharedFlow<String> = _navigateStreamerTwitch.asSharedFlow()

    private val _showMoreBottomSheetDialog : MutableSharedFlow<String> = MutableSharedFlow()
    val showMoreBottomSheetDialog : SharedFlow<String> = _showMoreBottomSheetDialog.asSharedFlow()

    fun fetchBookmarkedStreamData() = viewModelScope.launch{
        getBookmarkedStreamDataListUseCase()
            .onSuccess {
                _streamDataList.emit(it.toStreamViewTypeData())
            }.onFailure {

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