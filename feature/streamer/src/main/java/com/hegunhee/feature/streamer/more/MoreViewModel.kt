package com.hegunhee.feature.streamer.more

import androidx.lifecycle.ViewModel
import com.hegunhee.feature.streamer.MoreMenuActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor() : ViewModel(), MoreMenuActionHandler {

    override fun onClickDeleteStreamerButton() {
        
    }
}