package com.hegunhee.feature.streamer.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.twitch.SearchData
import com.hegunhee.domain.usecase.GetSearchStreamerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailStreamerViewModel @Inject constructor(private val getSearchStreamerDataUseCase: GetSearchStreamerDataUseCase) : ViewModel() {

    private val _streamerData : MutableStateFlow<SearchData> = MutableStateFlow(SearchData.EMPTY)
    val streamerData : StateFlow<SearchData> = _streamerData.asStateFlow()


    fun fetchStreamerData(streamerId : String){
        viewModelScope.launch {
            getSearchStreamerDataUseCase(streamerId)
                .onSuccess { searchData ->
                    _streamerData.value = searchData
                }.onFailure {

                }
        }
    }
}