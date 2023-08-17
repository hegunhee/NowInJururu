package com.hegunhee.ui_component.item

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hegunhee.resource_common.R

@Composable
fun OfflineStream(
    streamerId : String,
    streamerName : String,
    streamerProfileUrl : String,
    onTwitchStreamClick : (String) -> Unit,
    onUnfollowButtonClick : (String) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onTwitchStreamClick(streamerId) }
        .padding(
            start = dimensionResource(id = R.dimen.item_between_small_start_margin),
            top = dimensionResource(id = R.dimen.header_top_padding),
            bottom = dimensionResource(id = R.dimen.header_bottom_padding)
        ),
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(streamerProfileUrl).crossfade(true).build(),
            contentDescription = "streamerProfile",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(50.dp).weight(1f))
        Text(text = streamerName, fontSize= 20.sp,modifier = Modifier.weight(5f))
        Image(
            painter = painterResource(id = R.drawable.ic_menu_24),
            contentDescription = "more Button",
            modifier = Modifier.clickable { onUnfollowButtonClick(streamerId) }.weight(1f))
    }
}
