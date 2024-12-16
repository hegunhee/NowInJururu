package com.hegunhee.ui_component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.resource_common.R
import com.hegunhee.ui_component.style.headerTextFontSize

@Composable
fun ScreenHeaderText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        fontSize = headerTextFontSize,
        maxLines = 1,
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.header_start_padding),
            top = dimensionResource(id = R.dimen.header_top_padding),
            bottom = dimensionResource(id = R.dimen.header_bottom_padding),
            end = dimensionResource(id = R.dimen.header_end_padding)
        )
    )
}

@Preview
@Composable
private fun ScreenHeaderTextTest() {
    ScreenHeaderText(text = "TestText")
}