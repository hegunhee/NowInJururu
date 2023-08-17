package com.hegunhee.ui_component.item

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.hegunhee.resource_common.R

@Composable
fun OnlineStream(
    streamerId : String,
    streamerName : String,
    title : String,
    gameName : String,
    tags : List<String>,
    thumbNailUrl : String,
    profileUrl : String,
    viewerCount : String,
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
        verticalAlignment = Alignment.Top) {
        ThumbNailFormatImage(thumbNailUrl = thumbNailUrl, viewerCount = viewerCount)
        Spacer(modifier = Modifier.size(10.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(profileUrl).crossfade(true).build(),
                    contentDescription = "profileImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(20.dp).weight(1f)
                )
                Text(text = streamerName,modifier = Modifier.padding(start = dimensionResource(id = R.dimen.item_between_small_start_margin)).weight(5f))
                Image(painter = painterResource(id = R.drawable.ic_menu_24),
                    contentDescription = "more Button",
                    modifier = Modifier.weight(1f).clickable { onUnfollowButtonClick(streamerId) })
            }
            Text(text = title)
            Text(text = gameName)
            LazyRow() {
                items(items = tags,key = { it }) { tag ->
                    TagItem(tag)
                }
            }
        }
    }
}

@Composable
private fun ThumbNailFormatImage(
    thumbNailUrl: String,
    viewerCount: String
) {
    val thumbNailSize = Modifier.size(
        width = dimensionResource(id = R.dimen.online_thumb_nail_image_width_size),
        height = dimensionResource(id = R.dimen.online_thumb_nail_image_height_size)
    )
    Box() {
        Box(modifier = Modifier
            .then(thumbNailSize)
            .zIndex(0f)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(thumbNailUrl).crossfade(true).build(),
                contentDescription = "thumbNailImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .then(thumbNailSize)
            )
        }
        Box(modifier = Modifier
            .then(thumbNailSize)
            .zIndex(1f)
        ) {
            Row(modifier = Modifier.align(Alignment.BottomStart)) {
                Image(painter = painterResource(id = R.drawable.ic_online_dot_15), contentDescription = "online dot")
                Text(text = viewerCount)
            }
        }
    }
}
