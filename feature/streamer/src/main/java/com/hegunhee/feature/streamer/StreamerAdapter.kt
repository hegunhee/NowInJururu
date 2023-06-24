package com.hegunhee.feature.streamer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.feature.streamer.databinding.ItemLiveStreamerBinding
import com.hegunhee.feature.streamer.databinding.ItemLiveStreamerHeaderBinding
import com.hegunhee.feature.streamer.databinding.ItemUnLiveStreamerBinding
import com.hegunhee.feature.streamer.databinding.ItemUnLiveStreamerHeaderBinding
import com.squareup.picasso.Picasso

class StreamerAdapter(private val actionHandler : StreamActionHandler) : ListAdapter<StreamerViewType,StreamerAdapter.StreamerAdapterViewHolder>(DiffUtil) {

    sealed class StreamerAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(data: StreamerViewType)

    }

    inner class LiveStreamerHeaderViewHolder(private val binding : ItemLiveStreamerHeaderBinding) : StreamerAdapterViewHolder(binding.root) {
        override fun bindView(data: StreamerViewType) {
            val liveHeader = data as StreamerViewType.LiveStreamerHeader
            binding.size = liveHeader.size.toString()
            binding.executePendingBindings()
        }
    }

    inner class UnLiveStreamerHeaderViewHolder(private val binding : ItemUnLiveStreamerHeaderBinding) : StreamerAdapterViewHolder(binding.root) {
        override fun bindView(data: StreamerViewType) {
            val unLiveHeader = data as StreamerViewType.UnLiveStreamerHeader
            binding.size = unLiveHeader.size.toString()
            binding.executePendingBindings()
        }
    }

    inner class LiveStreamerViewHolder(private val binding : ItemLiveStreamerBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(data: StreamerViewType) {
            val liveStreamData = data as StreamerViewType.LiveStreamer
            binding.liveStreamData = liveStreamData
            binding.actionHandler = actionHandler
            binding.executePendingBindings()
        }
    }

    inner class UnLiveStreamerViewHolder(private val binding : ItemUnLiveStreamerBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(data: StreamerViewType) {
            val unLiveStreamData = data as StreamerViewType.UnLiveStreamer
            binding.unLiveStreamData = unLiveStreamData
            binding.actionHandler = actionHandler
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamerAdapterViewHolder {
        return when(viewType){
            R.layout.item_live_streamer_header -> {
                LiveStreamerHeaderViewHolder(ItemLiveStreamerHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_live_streamer ->{
                LiveStreamerViewHolder(ItemLiveStreamerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_un_live_streamer_header -> {
                UnLiveStreamerHeaderViewHolder(ItemUnLiveStreamerHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_un_live_streamer -> {
                UnLiveStreamerViewHolder(ItemUnLiveStreamerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> { throw IllegalArgumentException()}
        }
    }

    override fun onBindViewHolder(holder: StreamerAdapterViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is StreamerViewType.LiveStreamerHeader -> R.layout.item_live_streamer_header
            is StreamerViewType.LiveStreamer -> R.layout.item_live_streamer
            is StreamerViewType.UnLiveStreamerHeader -> R.layout.item_un_live_streamer_header
            is StreamerViewType.UnLiveStreamer -> R.layout.item_un_live_streamer
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