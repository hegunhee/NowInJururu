package com.hegunhee.feature.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.usecase.GetBookmarkedStreamDataListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamerViewModel @Inject constructor(
    private val getBookmarkedStreamDataListUseCase: GetBookmarkedStreamDataListUseCase
) : ViewModel() {

    private val _streamDataList : MutableStateFlow<List<StreamDataType>> = MutableStateFlow(emptyList())
    val streamDataList : StateFlow<List<StreamDataType>> = _streamDataList.asStateFlow()

    fun fetchBookmarkedStreamData() = viewModelScope.launch{
        getBookmarkedStreamDataListUseCase()
            .onSuccess {
                _streamDataList.emit(it)
            }.onFailure {
                
            }
    }
}