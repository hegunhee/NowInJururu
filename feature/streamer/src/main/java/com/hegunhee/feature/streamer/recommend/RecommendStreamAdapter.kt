package com.hegunhee.feature.streamer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.DiffUtil
import com.hegunhee.feature.streamer.databinding.ItemRecommendStreamBinding

class RecommendStreamAdapter(private val actionHandler: StreamActionHandler) : ListAdapter<StreamerViewType.OnlineStreamer, RecommendStreamAdapter.RecommendStreamViewHolder>(diff_util) {

    inner class RecommendStreamViewHolder(private val binding : ItemRecommendStreamBinding) : ViewHolder(binding.root){

        fun bind(streamData : StreamerViewType.OnlineStreamer) {
            binding.actionHandler = actionHandler
            binding.onlineStreamData = streamData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendStreamViewHolder {
        return RecommendStreamViewHolder(ItemRecommendStreamBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecommendStreamViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


internal object diff_util : DiffUtil.ItemCallback<StreamerViewType.OnlineStreamer>(){

    override fun areItemsTheSame(oldItem: StreamerViewType.OnlineStreamer, newItem: StreamerViewType.OnlineStreamer): Boolean {
        return oldItem.userLogin == newItem.userLogin
    }

    override fun areContentsTheSame(oldItem: StreamerViewType.OnlineStreamer, newItem: StreamerViewType.OnlineStreamer): Boolean {
        return oldItem == newItem
    }

}