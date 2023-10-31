package com.hegunhee.nowinjururu.core.designsystem.dialog.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.twitch.StreamerData
import com.hegunhee.domain.usecase.DeleteStreamerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val deleteStreamerDataUseCase: DeleteStreamerDataUseCase
) : ViewModel(), MoreMenuActionHandler {

    val streamerLogin : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _isSuccessDeleteStreamer : MutableSharedFlow<Boolean> = MutableSharedFlow<Boolean>()
    val isSuccessDeleteStreamer : SharedFlow<Boolean> = _isSuccessDeleteStreamer.asSharedFlow()

    fun fetchData(streamerName : String) {
        streamerLogin.value = streamerName
    }

    override fun onClickDeleteStreamerButton() {
        viewModelScope.launch {
            deleteStreamerDataUseCase(StreamerData(streamerLogin.value))
                .onSuccess {
                    _isSuccessDeleteStreamer.emit(true)
                }.onFailure {
                    _isSuccessDeleteStreamer.emit(false)
                }
        }
    }
}