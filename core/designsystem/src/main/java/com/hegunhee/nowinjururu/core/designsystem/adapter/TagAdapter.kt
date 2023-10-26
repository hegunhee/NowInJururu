package com.hegunhee.nowinjururu.core.designsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.nowinjururu.core.designsystem.databinding.ItemTagBinding

class TagAdapter : ListAdapter<String, TagAdapter.TagViewHolder>(diffUtil) {

    inner class TagViewHolder(private val binding : ItemTagBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(tagId : String) {
            binding.tag = tagId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(ItemTagBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
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