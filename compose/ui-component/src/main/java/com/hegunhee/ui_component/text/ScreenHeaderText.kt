package com.hegunhee.ui_component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeaderText(text : String) {
    Text(text = text, fontSize = 40.sp, maxLines = 1,modifier = Modifier.padding(
        start = 20.dp,
        top = 10.dp,
        bottom = 10.dp,
        end = 20.dp
    ))
}

@Preview
@Composable
private fun ScreenHeaderTextTest() {
    ScreenHeaderText(text = "TestText")
}