package com.hegunhee.feature.streamer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hegunhee.feature.streamer.databinding.ItemRecommendStreamContainerBinding
import androidx.recyclerview.widget.DiffUtil

class RecommendStreamContainerAdapter(private val actionHandler: StreamActionHandler) : ListAdapter<RecommendStreamContainerObject, RecommendStreamContainerAdapter.RecommendStreamContainerViewHolder>(recommend_container_diff_util){

    inner class RecommendStreamContainerViewHolder(private val binding : ItemRecommendStreamContainerBinding) : ViewHolder(binding.root) {
        fun bind(containerObject : RecommendStreamContainerObject) {
            binding.actionHandler = actionHandler
            binding.containerObject = containerObject
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendStreamContainerViewHolder {
        return RecommendStreamContainerViewHolder(ItemRecommendStreamContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecommendStreamContainerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

internal object recommend_container_diff_util : DiffUtil.ItemCallback<RecommendStreamContainerObject>() {
    override fun areItemsTheSame(oldItem: RecommendStreamContainerObject, newItem: RecommendStreamContainerObject): Boolean {
        return oldItem.item == newItem.item
    }

    override fun areContentsTheSame(oldItem: RecommendStreamContainerObject, newItem: RecommendStreamContainerObject): Boolean {
        return oldItem == newItem
    }

}

data class RecommendStreamContainerObject(val item : List<StreamerViewType.OnlineStreamer>)