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
import com.hegunhee.resource_common.R

@Composable
fun OfflineStream(
    streamerId : String,
    streamerName : String,
    streamerProfileUrl : String,
    onTwitchStreamClick : (String) -> Unit,
    onMoreButtonClick : (String) -> Unit
) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onTwitchStreamClick(String.format(context.getString(R.string.twitchChannelUrl),streamerId)) }
        .padding(
            start = dimensionResource(id = R.dimen.item_between_small_start_margin),
            top = dimensionResource(id = R.dimen.header_top_padding),
            bottom = dimensionResource(id = R.dimen.header_bottom_padding)
        ),
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = streamerProfileUrl,
            contentDescription = "streamerProfile",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(50.dp).weight(1f))
        Text(text = streamerName, fontSize= 20.sp,modifier = Modifier.weight(5f))
        Image(
            painter = painterResource(id = R.drawable.ic_menu_24),
            contentDescription = "more Button",
            modifier = Modifier.weight(1f).clickable { onMoreButtonClick(streamerId) })
    }
}

@Preview
@Composable
private fun TestOfflineStream() {
    val context = LocalContext.current
    OfflineStream(
        streamerId = "cotton__123",
        streamerName = "주르르",
        streamerProfileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-300x300.png",
        onTwitchStreamClick = { streamerId -> Toast.makeText(context,"click Item ",Toast.LENGTH_SHORT).show() },
        onMoreButtonClick =  { streamerId -> Toast.makeText(context,"click unfollowButton",Toast.LENGTH_SHORT).show()}
    )
}