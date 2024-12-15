package com.hegunhee.nowinjururu.feature.jururu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerAdapter
import com.hegunhee.nowinjururu.core.navigation.fragmentResultKeys.SearchTypeRequestKey
import com.hegunhee.nowinjururu.core.navigation.fragmentResultKeys.SortTypeRequestKey
import com.hegunhee.nowinjururu.feature.jururu.databinding.FragmentJururuBinding
import com.hegunhee.nowinjururu.feature.searchkakao.KakaoSearchAdapter
import com.hegunhee.nowinjururu.feature.searchkakao.searchfilter.KakaoSearchFilterDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JururuFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentJururuBinding
    private lateinit var streamerAdapter : StreamerAdapter
    private lateinit var searchAdapter : KakaoSearchAdapter
    private lateinit var concatAdapter : ConcatAdapter
    private val viewModel : JururuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_jururu,container,false)
        streamerAdapter = StreamerAdapter(viewModel)
        searchAdapter = KakaoSearchAdapter(viewModel)
        concatAdapter = ConcatAdapter().apply {
            addAdapter(streamerAdapter)
            addAdapter(searchAdapter)
        }
        viewDataBinding = FragmentJururuBinding.bind(root).apply {
            viewModel = this@JururuFragment.viewModel
            jururuRecyclerview.adapter = concatAdapter
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        fragmentResultListener()
    }

    private fun observeData() {
        observePagingData()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.favoriteStreamData.collect{
                        streamerAdapter.submitList(listOf(it))
                    }
                }
                launch {
                    viewModel.navigateDeepLink.collect{ deepLink ->
                        deepLink.handleDeepLink(requireContext())
                    }
                }
                launch {
                    viewModel.navigateKakaoFilter.collect {
                        KakaoSearchFilterDialogFragment(it).show(childFragmentManager,KakaoSearchFilterDialogFragment.TAG)
                    }
                }
            }
        }
    }

    private fun observePagingData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.kakaoSearchData.collectLatest {
                        searchAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun fragmentResultListener() {
        childFragmentManager.setFragmentResultListener(SearchTypeRequestKey,viewLifecycleOwner) { _, bundle ->
            val name = bundle.getString("name") ?: KakaoSearchType.Default.name
            viewModel.setSearchType(KakaoSearchType.findType(name))
            observePagingData()
        }
        childFragmentManager.setFragmentResultListener(SortTypeRequestKey,viewLifecycleOwner) { _, bundle ->
            val name = bundle.getString("name") ?: KakaoSearchSortType.Recency.name
            viewModel.setSortType(KakaoSearchSortType.findType(name))
            observePagingData()
        }
    }
}