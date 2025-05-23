package com.hegunhee.feature.streamer.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hegunhee.domain.model.kakao.KakaoSearchSortType
import com.hegunhee.domain.model.kakao.KakaoSearchType
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.databinding.FragmentDetailStreamerBinding
import com.hegunhee.nowinjururu.core.navigation.fragmentResultKeys.SearchTypeRequestKey
import com.hegunhee.nowinjururu.core.navigation.fragmentResultKeys.SortTypeRequestKey
import com.hegunhee.nowinjururu.feature.searchkakao.KakaoSearchAdapter
import com.hegunhee.nowinjururu.feature.searchkakao.searchfilter.KakaoSearchFilterDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailStreamerFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentDetailStreamerBinding
    private val viewModel : DetailStreamerViewModel by viewModels()
    private lateinit var searchAdapter : KakaoSearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireStreamerIdArgument(arguments)
        searchAdapter = KakaoSearchAdapter(viewModel)
        val root = inflater.inflate(R.layout.fragment_detail_streamer,container,false)
        viewDataBinding = FragmentDetailStreamerBinding.bind(root).apply {
            viewModel = this@DetailStreamerFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            streamerRecyclerView.adapter = searchAdapter
        }
        return root
    }

    private fun requireStreamerIdArgument(argument : Bundle?) {
        argument?.getString("streamerId")?.let { streamerId ->
            viewModel.fetchStreamerData(streamerId)
        } ?: {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        observePagingData()
        fragmentResultListener()
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

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.streamerData.collect {
                        if(!it.isEmpty()) {
                            viewModel.getWebSearchData(it.streamerName)
                            observePagingData()
                        }
                    }
                }
                launch {
                    viewModel.navigateDeepLink.collect{ deepLink ->
                        deepLink.handleDeepLink(requireContext())
                    }
                }
                launch {
                    viewModel.navigationEvent.collect {
                        when(it){
                            is DetailStreamerNavigationEvent.Back -> {
                                findNavController().popBackStack()
                            }
                        }
                    }
                }
                launch {
                    viewModel.navigateKakaoFilter.collect {
                        KakaoSearchFilterDialogFragment(it).show(childFragmentManager, KakaoSearchFilterDialogFragment.TAG)
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