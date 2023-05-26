package com.hegunhee.feature.streamer.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.model.StreamerData
import com.hegunhee.domain.usecase.DeleteStreamerDataUseCase
import com.hegunhee.feature.streamer.MoreMenuActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val deleteStreamerDataUseCase: DeleteStreamerDataUseCase
) : ViewModel(), MoreMenuActionHandler {

    val streamerLogin : MutableStateFlow<String> = MutableStateFlow<String>("")

    fun fetchData(streamerName : String) {
        streamerLogin.value = streamerName
    }

    override fun onClickDeleteStreamerButton() {
        viewModelScope.launch {
            deleteStreamerDataUseCase(StreamerData(streamerLogin.value))
        }
    }
}