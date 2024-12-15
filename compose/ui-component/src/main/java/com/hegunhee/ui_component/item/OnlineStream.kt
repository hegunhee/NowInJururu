package com.hegunhee.ui_component.item

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.hegunhee.domain.model.platform.StreamPlatform
import com.hegunhee.domain.model.platform.TwitchStreamer
import com.hegunhee.nowinjururu.core.navigation.deeplink.mapper.toDeepLink
import com.hegunhee.resource_common.R

@Composable
fun OnlineStream(
    platform: StreamPlatform,
    streamerId : String,
    streamerName : String,
    title : String,
    gameName : String,
    tags : List<String>,
    thumbNailUrl : String,
    profileUrl : String,
    viewerCount : String,
    onMoreButtonClick : (String) -> Unit,
    context : Context,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { platform.toDeepLink().handleDeepLink(context) }
        .padding(
            start = dimensionResource(id = R.dimen.header_start_padding),
            top = dimensionResource(id = R.dimen.header_top_padding),
            bottom = dimensionResource(id = R.dimen.header_bottom_padding)
        ),
        verticalAlignment = Alignment.Top) {
        ThumbNailFormatImage(thumbNailUrl = thumbNailUrl, viewerCount = viewerCount)
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.item_between_start_margin)))
        Column(modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = profileUrl,
                    contentDescription = "profileImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(dimensionResource(R.dimen.profile_image_small_size)).weight(1f)
                )
                Text(text = streamerName,modifier = Modifier.padding(start = dimensionResource(id = R.dimen.item_between_small_start_margin)).weight(5f))
                Image(painter = painterResource(id = R.drawable.ic_menu_24),
                    contentDescription = "more Button",
                    modifier = Modifier.weight(1f).clickable { onMoreButtonClick(streamerId) })
            }
            Text(text = title,maxLines = 1)
            Text(text = gameName,maxLines = 1)
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
                model = thumbNailUrl,
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

@Preview
@Composable
private fun TestOnlineStream() {
    val context = LocalContext.current
    OnlineStream(
        platform = TwitchStreamer("cotton__123"),
        streamerId = "cotton__123",
        streamerName = "주르르",
        title = "방송입니다.",
        gameName = "Just Chatting",
        tags = listOf("이세계아이돌","주르르","콘르르"),
        thumbNailUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/aea85c64-5e28-4d15-81a1-db1a7a3cc1ec-channel_offline_image-1920x1080.png",
        profileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-70x70.png",
        viewerCount = "1000",
        onMoreButtonClick =  {streamerId -> Toast.makeText(context,"click unfollowButton", Toast.LENGTH_SHORT).show()},
        context = context,
    )
}
