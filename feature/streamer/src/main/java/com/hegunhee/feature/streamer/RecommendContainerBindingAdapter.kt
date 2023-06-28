package com.hegunhee.feature.streamer

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("actionHandler","recommendItems")
fun RecyclerView.setRecommendStreamAdapter(actionHandler: StreamActionHandler,recommendStreamList : List<StreamerViewType.OnlineStreamer>) {
    val adapter = RecommendStreamAdapter(actionHandler)
    this.adapter = adapter
    adapter.submitList(recommendStreamList)
}