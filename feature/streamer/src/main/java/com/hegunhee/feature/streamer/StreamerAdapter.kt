package com.hegunhee.feature.streamer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.hegunhee.feature.streamer.databinding.*

class StreamerAdapter(private val actionHandler : StreamActionHandler) : ListAdapter<StreamerViewType,StreamerAdapter.StreamerAdapterViewHolder>(DiffUtil) {

    sealed class StreamerAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(data: StreamerViewType)

    }

    inner class OnlineStreamerHeaderViewHolder(private val binding : ItemOnlineStreamHeaderBinding) : StreamerAdapterViewHolder(binding.root) {
        override fun bindView(data: StreamerViewType) {
            val liveHeader = data as StreamerViewType.OnlineStreamerHeader
            binding.size = liveHeader.size.toString()
        }
    }

    inner class OfflineStreamerHeaderViewHolder(private val binding : ItemOfflineStreamHeaderBinding) : StreamerAdapterViewHolder(binding.root) {
        override fun bindView(data: StreamerViewType) {
            val unLiveHeader = data as StreamerViewType.OfflineStreamerHeader
            binding.size = unLiveHeader.size.toString()
        }
    }

    inner class OnlineStreamerViewHolder(private val binding : ItemOnlineStreamBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(data: StreamerViewType) {
            val liveStreamData = data as StreamerViewType.OnlineStreamer
            binding.onlineStreamData = liveStreamData
            binding.actionHandler = actionHandler
        }
    }

    inner class OfflineStreamerViewHolder(private val binding : ItemOfflineStreamBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(data: StreamerViewType) {
            val unLiveStreamData = data as StreamerViewType.OfflineStreamer
            binding.offlineStreamData = unLiveStreamData
            binding.actionHandler = actionHandler
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamerAdapterViewHolder {
        return when(viewType){
            R.layout.item_online_stream_header -> {
                OnlineStreamerHeaderViewHolder(ItemOnlineStreamHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_online_stream ->{
                OnlineStreamerViewHolder(ItemOnlineStreamBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_offline_stream_header -> {
                OfflineStreamerHeaderViewHolder(ItemOfflineStreamHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_offline_stream -> {
                OfflineStreamerViewHolder(ItemOfflineStreamBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> { throw IllegalArgumentException()}
        }
    }

    override fun onBindViewHolder(holder: StreamerAdapterViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is StreamerViewType.OnlineStreamerHeader -> R.layout.item_online_stream_header
            is StreamerViewType.OnlineStreamer -> R.layout.item_online_stream
            is StreamerViewType.OfflineStreamerHeader -> R.layout.item_offline_stream_header
            is StreamerViewType.OfflineStreamer -> R.layout.item_offline_stream
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