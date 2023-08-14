package com.hegunhee.compose.jururu

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.domain.usecase.GetJururuStreamDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JururuViewModel @Inject constructor(private val getJururuStreamDataUseCase: GetJururuStreamDataUseCase) : ViewModel(){

    init{
        getJururuStreamData()
    }

    private val _uiState : MutableState<JururuUiState> = mutableStateOf<JururuUiState>(JururuUiState.Loading)
    val uiState : State<JururuUiState>
        get() = _uiState

    private fun getJururuStreamData() {
        viewModelScope.launch {
            getJururuStreamDataUseCase()
                .onSuccess {
                    _uiState.value = JururuUiState.Success(listOf(it))
                }.onFailure {
                    _uiState.value = JururuUiState.Error
                }
        }

    }


}