package com.hegunhee.nowinjururu.feature.searchkakao

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hegunhee.domain.model.kakao.KakaoSearchData
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.ItemSearchImageBinding
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.ItemSearchVideoBinding
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.ItemSearchWebBinding


abstract class KakaoSearchViewHolder(private val itemView : View) : ViewHolder(itemView) {
    abstract fun bindView(actionHandler: KakaoSearchActionHandler, searchData : KakaoSearchData)

}

class KakaoSearchWebViewHolder(private val binding : ItemSearchWebBinding) : KakaoSearchViewHolder(binding.root) {

    override fun bindView(actionHandler: KakaoSearchActionHandler, searchData: KakaoSearchData) {
        binding.actionHandler = actionHandler
        (searchData as? KakaoSearchData.Web)?.let {  webData ->
            binding.webData = webData
        }
    }
}

class KakaoSearchVideoViewHolder(private val binding : ItemSearchVideoBinding) : KakaoSearchViewHolder(binding.root) {

    override fun bindView(actionHandler: KakaoSearchActionHandler, searchData: KakaoSearchData) {
        (searchData as? KakaoSearchData.Video)?.let { videoData ->
            binding.title.text = videoData.title
        }
    }
}

class KakaoSearchImageViewHolder(private val binding : ItemSearchImageBinding) : KakaoSearchViewHolder(binding.root) {
    override fun bindView(actionHandler: KakaoSearchActionHandler, searchData: KakaoSearchData) {
        (searchData as? KakaoSearchData.Image)?.let { imageData ->
            binding.title.text = imageData.displaySiteName
        }
    }
}