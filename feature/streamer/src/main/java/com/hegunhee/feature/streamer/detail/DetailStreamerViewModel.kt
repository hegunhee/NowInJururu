package com.hegunhee.feature.streamer.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.usecase.GetSearchStreamerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailStreamerViewModel @Inject constructor(private val getSearchStreamerDataUseCase: GetSearchStreamerDataUseCase) : ViewModel() {

    private val _streamerData : MutableStateFlow<SearchData> = MutableStateFlow(SearchData.EMPTY)
    val streamerData : StateFlow<SearchData> = _streamerData.asStateFlow()

    private val _navigationEvent : MutableSharedFlow<DetailStreamerNavigationEvent> = MutableSharedFlow()
    val navigationEvent : SharedFlow<DetailStreamerNavigationEvent> = _navigationEvent.asSharedFlow()


    fun fetchStreamerData(streamerId : String){
        viewModelScope.launch {
            getSearchStreamerDataUseCase(streamerId)
                .onSuccess { searchData ->
                    _streamerData.value = searchData
                }.onFailure {

                }
        }
    }

    fun onBackButtonClick() {
        viewModelScope.launch {
            _navigationEvent.emit(DetailStreamerNavigationEvent.Back)
        }
    }
}