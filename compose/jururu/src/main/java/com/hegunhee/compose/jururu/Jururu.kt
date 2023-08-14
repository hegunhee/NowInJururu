package com.hegunhee.compose.jururu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun JururuScreenRoot(

) {
    JururuScreen()
}

@Composable
fun JururuScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(LocalPaddingValues.current)) {
        ScreenHeaderText(text = "주르르")
    }
}

