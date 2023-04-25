package com.hegunhee.feature.streamer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.feature.streamer.databinding.ItemLiveStreamerBinding
import com.hegunhee.feature.streamer.databinding.ItemUnLiveStreamerBinding
import com.squareup.picasso.Picasso

class StreamerAdapter() : ListAdapter<StreamDataType,StreamerAdapter.StreamerAdapterViewHolder>(DiffUtil) {

    sealed class StreamerAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(memo: StreamDataType)

    }

    inner class LiveStreamerViewHolder(private val binding : ItemLiveStreamerBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(memo: StreamDataType) {
        }
    }

    inner class UnLiveStreamerViewHolder(private val binding : ItemUnLiveStreamerBinding) : StreamerAdapterViewHolder(binding.root){
        override fun bindView(data: StreamDataType) {
            val emptyData = data as StreamDataType.EmptyData
            binding.streamerInfo = emptyData
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamerAdapterViewHolder {
        return when(viewType){
            R.layout.item_live_streamer ->{
                LiveStreamerViewHolder(ItemLiveStreamerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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
            is StreamDataType.StreamData -> R.layout.item_live_streamer
            is StreamDataType.EmptyData -> R.layout.item_un_live_streamer
        }
    }

    companion object {
         val JururuStreamInfo = StreamDataType.EmptyData(userLogin = "cotton__123", userName = "주르르",profileUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/919e1ba0-e13e-49ae-a660-181817e3970d-profile_image-70x70.png")
    }
}

internal object DiffUtil : DiffUtil.ItemCallback<StreamDataType>(){

    override fun areItemsTheSame(oldItem: StreamDataType, newItem: StreamDataType): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: StreamDataType, newItem: StreamDataType): Boolean {
        return oldItem == newItem
    }

}