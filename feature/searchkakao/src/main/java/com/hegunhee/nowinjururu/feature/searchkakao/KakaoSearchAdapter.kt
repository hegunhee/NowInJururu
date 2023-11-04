package com.hegunhee.nowinjururu.feature.searchkakao

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.ItemSearchImageBinding
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.ItemSearchVideoBinding
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.ItemSearchWebBinding
import kotlin.IllegalArgumentException

class KakaoSearchAdapter(private val kakaoSearchActionHandler : KakaoSearchActionHandler) : PagingDataAdapter<KakaoSearchData,KakaoSearchViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoSearchViewHolder {
        return when(viewType) {
            R.layout.item_search_web -> {KakaoSearchWebViewHolder(ItemSearchWebBinding.inflate(LayoutInflater.from(parent.context),parent,false)) }
            R.layout.item_search_image -> { KakaoSearchImageViewHolder(ItemSearchImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))}
            R.layout.item_search_video -> { KakaoSearchVideoViewHolder(ItemSearchVideoBinding.inflate(LayoutInflater.from(parent.context),parent,false)) }
            else -> { throw IllegalArgumentException("") }
        }
    }

    override fun onBindViewHolder(holder: KakaoSearchViewHolder, position: Int) {
        getItem(position)?.let { searchData ->
            holder.bindView(kakaoSearchActionHandler,searchData)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is KakaoSearchData.Web -> R.layout.item_search_web
            is KakaoSearchData.Image -> R.layout.item_search_image
            is KakaoSearchData.Video -> R.layout.item_search_video
            else -> { throw IllegalArgumentException()}
        }
    }
    companion object {
        private object diffUtil : DiffUtil.ItemCallback<KakaoSearchData>() {
            override fun areItemsTheSame(oldItem: KakaoSearchData, newItem: KakaoSearchData): Boolean {
                return oldItem.type.name + oldItem.time == newItem.type.name + newItem.time
            }

            override fun areContentsTheSame(oldItem: KakaoSearchData, newItem: KakaoSearchData): Boolean {
                return oldItem == newItem
            }

        }
    }
}