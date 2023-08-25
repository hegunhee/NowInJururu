package com.hegunhee.ui_component.item

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.hegunhee.resource_common.R
import com.hegunhee.ui_component.style.middleTextFontSize

@Composable
fun RecommendStream(
    streamerId : String,
    streamerName : String,
    title : String,
    gameName : String,
    tags : List<String>,
    thumbNailUrl : String,
    profileUrl : String,
    viewerCount : String,
    onTwitchStreamClick : (String) -> Unit,
) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .clickable {  onTwitchStreamClick(String.format(context.getString(R.string.twitchChannelUrl),streamerId)) }
        .padding(horizontal = dimensionResource(R.dimen.header_start_padding))
    ) {
        ThumbNailFormatImage(thumbNailUrl = thumbNailUrl, viewerCount = viewerCount)
        Spacer(modifier = Modifier.height(height = dimensionResource((R.dimen.item_top_margin))))
        Row() {
            AsyncImage(
                model = profileUrl,
                contentDescription = "프로필 사진",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(dimensionResource(R.dimen.profile_image_middle_size))
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.item_between_small_top_margin)))
            Column() {
                Text(text = streamerName)
                Text(text = title,fontSize = middleTextFontSize,maxLines = 1,modifier = Modifier.widthIn(min = 1.dp,max = 150.dp))
                Text(text = gameName,fontSize = middleTextFontSize,maxLines = 1,modifier = Modifier.widthIn(min = 1.dp,max = 150.dp))
                LazyRow(modifier = Modifier.widthIn(min = 1.dp,max = 150.dp)) {
                    items(items = tags,key = { it }) { tag ->
                        TagItem(tag)
                    }
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
    val thumbNailSize = Modifier.size(dimensionResource(R.dimen.thumbnail_image_large_size))
    Box() {
        Box(modifier = Modifier
            .then(thumbNailSize)
            .zIndex(0f)) {
            AsyncImage(
                model = thumbNailUrl,
                contentDescription = "thumbNailImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.then(thumbNailSize)
            )
        }
        Box(modifier = Modifier
            .then(thumbNailSize)
            .zIndex(1f)
        ) {
            Row(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(text = "총 시청자 : $viewerCount",color = Color.White,modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black))
            }
        }
    }
}

@Preview
@Composable
private fun TestRecommendStream() {
    val context = LocalContext.current
    RecommendStream(
        streamerId = "cotton__123",
        streamerName = "주르르",
        title = "방송입니다.",
        gameName = "Just Chatting",
        tags = listOf("이세계아이돌","주르르","콘르르"),
        thumbNailUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/aea85c64-5e28-4d15-81a1-db1a7a3cc1ec-channel_offline_image-1920x1080.png",
        profileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-70x70.png",
        viewerCount = "1000",
        onTwitchStreamClick = { streamerId -> Toast.makeText(context,"click Item ", Toast.LENGTH_SHORT).show() },
    )
}
