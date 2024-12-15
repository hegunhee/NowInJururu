package com.hegunhee.ui_component.item

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.hegunhee.domain.model.platform.StreamPlatform
import com.hegunhee.domain.model.platform.TwitchStreamer
import com.hegunhee.nowinjururu.core.navigation.deeplink.mapper.toDeepLink
import com.hegunhee.resource_common.R
import com.hegunhee.ui_component.style.largeTextFontSize

@Composable
fun OfflineStream(
    platform: StreamPlatform,
    streamerId : String,
    streamerName : String,
    streamerProfileUrl : String,
    onMoreButtonClick : (String) -> Unit
) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { platform.toDeepLink().handleDeepLink(context) }
        .padding(
            start = dimensionResource(id = R.dimen.header_start_padding),
            top = dimensionResource(id = R.dimen.header_top_padding),
            bottom = dimensionResource(id = R.dimen.header_bottom_padding)
        ),
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = streamerProfileUrl,
            contentDescription = "streamerProfile",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(dimensionResource(id = R.dimen.profile_image_middle_size)).weight(1f))
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.item_between_start_margin)))
        Text(text = streamerName, fontSize= largeTextFontSize,modifier = Modifier.weight(5f))
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
        platform = TwitchStreamer("cotton__123"),
        streamerId = "cotton__123",
        streamerName = "주르르",
        streamerProfileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-300x300.png",
        onMoreButtonClick =  { streamerId -> Toast.makeText(context,"click unfollowButton",Toast.LENGTH_SHORT).show()},
    )
}
