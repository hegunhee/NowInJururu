package com.hegunhee.nowinjururu.core.designsystem.adapter.recommend

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamActionHandler
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerViewType

@BindingAdapter("actionHandler","recommendItems")
fun RecyclerView.setRecommendStreamAdapter(actionHandler: StreamActionHandler, recommendStreamList : List<StreamerViewType.OnlineStreamer>) {
    val adapter = RecommendStreamAdapter(actionHandler)
    this.adapter = adapter
    adapter.submitList(recommendStreamList)
}