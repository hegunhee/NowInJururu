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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hegunhee.resource_common.R

@Composable
fun SearchStreamer(
    streamerId : String,
    streamerName : String,
    profileUrl : String,
    onItemClick : (String) -> Unit,
    onFollowButtonClick : (String) -> Unit
) {
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth().clickable { onItemClick(String.format(context.getString(R.string.twitchChannelUrl),streamerId)) }) {
        AsyncImage(
            model = profileUrl,
            contentDescription = "$streamerName 입니다.",
            modifier = Modifier.size(100.dp).padding(vertical = 10.dp).alignByBaseline()
        )
        Column(
            modifier = Modifier.padding(start = 10.dp).align(Alignment.Bottom),
        ) {
            Text(text = streamerName,fontSize = 15.sp)
            Text(text = streamerId, fontSize = 15.sp)
            Button(onClick = {onFollowButtonClick(streamerId)}) {
                Text(text = "즐겨찾기",fontSize =13.sp)
            }
        }
    }

}