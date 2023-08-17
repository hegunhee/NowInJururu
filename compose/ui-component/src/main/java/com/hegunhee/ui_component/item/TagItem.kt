package com.hegunhee.ui_component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.hegunhee.resource_common.R

@Composable
fun TagItem(tag : String) {
    Text(text = tag,modifier = Modifier
        .padding(end = dimensionResource(id = R.dimen.item_between_small_start_margin))
        .background(color = Color.Black, shape = RoundedCornerShape(8.dp)),
        color = Color.White)
}