package com.hegunhee.ui_component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hegunhee.resource_common.R

@Composable
fun ScreenHeaderText(text : String) {
    Text(text = text, fontSize = 40.sp, maxLines = 1,modifier = Modifier.padding(
        start = dimensionResource(id = R.dimen.header_start_padding),
        top = dimensionResource(id = R.dimen.header_top_padding),
        bottom = dimensionResource(id = R.dimen.header_bottom_padding),
        end = dimensionResource(id = R.dimen.header_end_padding)
    ))

}

@Preview
@Composable
private fun ScreenHeaderTextTest() {
    ScreenHeaderText(text = "TestText")
}