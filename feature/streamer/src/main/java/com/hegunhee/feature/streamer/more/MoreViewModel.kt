package com.hegunhee.feature.streamer.more

import androidx.lifecycle.ViewModel
import com.hegunhee.feature.streamer.MoreMenuActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor() : ViewModel(), MoreMenuActionHandler {

    val streamerLogin : MutableStateFlow<String> = MutableStateFlow<String>("")
    
    fun fetchData(streamerName : String) {
        streamerLogin.value = streamerName
    }

    override fun onClickDeleteStreamerButton() {

    }
}