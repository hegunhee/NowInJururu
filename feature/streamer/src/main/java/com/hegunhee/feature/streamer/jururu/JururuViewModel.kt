package com.hegunhee.feature.streamer.jururu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.domain.usecase.GetJururuStreamDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(private val getJururuStreamDataUseCase: GetJururuStreamDataUseCase) : ViewModel() {

    private val _jururuStreamData : MutableStateFlow<StreamDataType> = MutableStateFlow(StreamDataType.EmptyData("","",""))
    val jururuStreamData : StateFlow<StreamDataType> = _jururuStreamData.asStateFlow()

    fun getJururuStreamData() {
        viewModelScope.launch {
            getJururuStreamDataUseCase()
                .onSuccess {
                    _jururuStreamData.emit(it)
                }
                .onFailure {  }
        }
    }
}