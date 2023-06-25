package com.hegunhee.feature.streamer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.hegunhee.feature.streamer.databinding.ItemOfflineStreamerBinding
import com.hegunhee.feature.streamer.databinding.ItemOfflineStreamerHeaderBinding
import com.hegunhee.feature.streamer.databinding.ItemOnlineStreamerBinding
import com.hegunhee.feature.streamer.databinding.ItemOnlineStreamerHeaderBinding

class StreamerAdapter(private val actionHandler : StreamActionHandler) : ListAdapter<StreamerViewType,StreamerAdapter.StreamerAdapterViewHolder>(DiffUtil) {

    sealed class StreamerAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(data: StreamerViewType)

    }

    inner class OnlineStreamerHeaderViewHolder(private val binding : ItemOnlineStreamerHeaderBinding) : StreamerAdapterViewHolder(binding.root) {
        override fun bindView(data: StreamerViewType) {
            val liveHeader = data as StreamerViewType.OnlineStreamerHeader
            binding.size = liveHeader.size.toString()
            binding.executePendingBindings()
        }
    }

    inner class OfflineStreamerHeaderViewHolder(private val binding : ItemOfflineStreamerHeaderBinding) : StreamerAdapterViewHolder(binding.root) {
        override fun bindView(data: StreamerViewType) {
            val unLiveHeader = data as StreamerViewType.OfflineStreamerHeader
            binding.size = unLiveHeader.size.toString()
            binding.executePendingBindings()
        }
    }

    inner class OnlineStreamerViewHolder(private val binding : ItemOnlineStreamerBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(data: StreamerViewType) {
            val liveStreamData = data as StreamerViewType.OnlineStreamer
            binding.onlineStreamData = liveStreamData
            binding.actionHandler = actionHandler
            binding.executePendingBindings()
        }
    }

    inner class OfflineStreamerViewHolder(private val binding : ItemOfflineStreamerBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(data: StreamerViewType) {
            val unLiveStreamData = data as StreamerViewType.OfflineStreamer
            binding.offlineStreamData = unLiveStreamData
            binding.actionHandler = actionHandler
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamerAdapterViewHolder {
        return when(viewType){
            R.layout.item_online_streamer_header -> {
                OnlineStreamerHeaderViewHolder(ItemOnlineStreamerHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_online_streamer ->{
                OnlineStreamerViewHolder(ItemOnlineStreamerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_offline_streamer_header -> {
                OfflineStreamerHeaderViewHolder(ItemOfflineStreamerHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_offline_streamer -> {
                OfflineStreamerViewHolder(ItemOfflineStreamerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> { throw IllegalArgumentException()}
        }
    }

    override fun onBindViewHolder(holder: StreamerAdapterViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is StreamerViewType.OnlineStreamerHeader -> R.layout.item_online_streamer_header
            is StreamerViewType.OnlineStreamer -> R.layout.item_online_streamer
            is StreamerViewType.OfflineStreamerHeader -> R.layout.item_offline_streamer_header
            is StreamerViewType.OfflineStreamer -> R.layout.item_offline_streamer
        }
    }
}

internal object DiffUtil : DiffUtil.ItemCallback<StreamerViewType>(){

    override fun areItemsTheSame(oldItem: StreamerViewType, newItem: StreamerViewType): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: StreamerViewType, newItem: StreamerViewType): Boolean {
        return oldItem == newItem
    }

}