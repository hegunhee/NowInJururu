package com.hegunhee.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.domain.model.SearchData
import androidx.recyclerview.widget.DiffUtil
import com.hegunhee.feature.search.databinding.ItemStreamerBinding

class SearchAdapter(private val actionHandler: SearchActionHandler) : PagingDataAdapter<SearchData, SearchAdapter.SearchViewHolder>(diffUtil) {

    inner class SearchViewHolder(private val binding: ItemStreamerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(searchData: SearchData) {
            binding.streamerInfo = searchData
            binding.actionHandler = actionHandler
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemStreamerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindView(it)
        }
    }

    companion object {
        private object diffUtil : DiffUtil.ItemCallback<SearchData>() {
            override fun areItemsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
                return oldItem.streamerId == newItem.streamerId
            }

            override fun areContentsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
                return oldItem == newItem
            }
        }
    }
}