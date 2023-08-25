package com.hegunhee.ui_component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hegunhee.resource_common.R
import com.hegunhee.ui_component.style.middleButtonFontSize
import com.hegunhee.ui_component.style.middleTextFontSize

@Composable
fun SearchStreamer(
    streamerId : String,
    streamerName : String,
    profileUrl : String,
    onItemClick : (String) -> Unit,
    onFollowButtonClick : (String) -> Unit
) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(String.format(context.getString(R.string.twitchChannelUrl),streamerId)) }
        .padding(horizontal = dimensionResource(R.dimen.header_start_padding))) {
        AsyncImage(
            model = profileUrl,
            contentDescription = "$streamerName 입니다.",
            modifier = Modifier.size(dimensionResource(R.dimen.search_profile_image_height_size)).padding(vertical = dimensionResource(R.dimen.search_profile_image_vertical_padding)).alignByBaseline()
        )
        Column(
            modifier = Modifier.padding(start = dimensionResource(R.dimen.item_between_start_margin)).align(Alignment.Bottom),
        ) {
            Text(text = streamerName,fontSize = middleTextFontSize)
            Text(text = streamerId, fontSize = middleTextFontSize)
            Button(onClick = {onFollowButtonClick(streamerId)}) {
                Text(text = "즐겨찾기",fontSize = middleButtonFontSize)
            }
        }
    }
}