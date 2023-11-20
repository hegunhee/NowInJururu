package com.hegunhee.nowinjururu.feature.searchkakao.searchfilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hegunhee.domain.model.kakao.KakaoFilter
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.nowinjururu.core.navigation.fragmentResultKeys.SearchTypeRequestKey
import com.hegunhee.nowinjururu.core.navigation.fragmentResultKeys.SortTypeRequestKey
import com.hegunhee.nowinjururu.feature.searchkakao.R
import com.hegunhee.nowinjururu.feature.searchkakao.databinding.DialogSearchFilterBinding

class KakaoSearchFilterDialogFragment(private val filterType : KakaoFilter) : BottomSheetDialogFragment() {

    private lateinit var viewDataBinding : DialogSearchFilterBinding
    private val filterAdapter : FilterItemAdapter by lazy { FilterItemAdapter { name ->
        onFilterItemClick(name)
    } }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_search_filter,container,false)
        viewDataBinding = DialogSearchFilterBinding.bind(root).apply {
            lifecycleOwner = viewLifecycleOwner
            filterRecyclerView.adapter = filterAdapter
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.filterDesc.text = filterType.type
        filterAdapter.submitList(filterType.filterList.toList())

    }

    private fun onFilterItemClick(name : String) {
        when(filterType.type) {
            KakaoSearchType.TYPE -> {
                parentFragmentManager.setFragmentResult(SearchTypeRequestKey, bundleOf("name" to name))
            }
            KakaoSearchSortType.TYPE -> {
                parentFragmentManager.setFragmentResult(SortTypeRequestKey, bundleOf("name" to name))
            }
        }
        dismissAllowingStateLoss()
    }

    companion object {
        const val TAG = "SearchFilterDialog"
    }
}