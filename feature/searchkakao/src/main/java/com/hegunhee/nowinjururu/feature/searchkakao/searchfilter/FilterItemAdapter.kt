package com.hegunhee.nowinjururu.feature.searchkakao.searchfilter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.ItemFilterBinding

class FilterItemAdapter(private val onClickItem : (String) -> Unit) : ListAdapter<String,FilterItemAdapter.FilterItemViewHolder>(diffUtil) {

    inner class FilterItemViewHolder(private val binding : ItemFilterBinding) : ViewHolder(binding.root) {

        fun bindView(name : String) {
            binding.apply {
                filterItem.text = name
                filterItem.setOnClickListener {
                    onClickItem(name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterItemViewHolder {
        return FilterItemViewHolder(ItemFilterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FilterItemViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {
        private object diffUtil : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }
}