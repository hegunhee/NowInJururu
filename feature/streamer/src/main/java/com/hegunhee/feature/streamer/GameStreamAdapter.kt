package com.hegunhee.feature.streamer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.DiffUtil
import com.hegunhee.feature.streamer.databinding.ItemGameStreamBinding

class GameStreamAdapter(private val actionHandler: StreamActionHandler) : ListAdapter<StreamerViewType.OnlineStreamer, GameStreamAdapter.GameStreamViewHolder>(diff_util) {

    inner class GameStreamViewHolder(private val binding : ItemGameStreamBinding) : ViewHolder(binding.root){

        fun bind(streamData : StreamerViewType.OnlineStreamer) {
            binding.actionHandler = actionHandler
            binding.onlineStreamData = streamData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameStreamViewHolder {
        return GameStreamViewHolder(ItemGameStreamBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GameStreamViewHolder, position: Int) {
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