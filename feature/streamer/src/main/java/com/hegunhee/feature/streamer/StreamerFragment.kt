package com.hegunhee.feature.streamer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import com.hegunhee.feature.common.fragmentResultKeys.streamRequestKey
import com.hegunhee.feature.common.twitch.handleTwitchDeepLink
import com.hegunhee.feature.streamer.databinding.FragmentStreamerBinding
import com.hegunhee.feature.streamer.more.MoreBottomDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StreamerFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentStreamerBinding
    private val viewModel : StreamerViewModel by viewModels()
    private lateinit var streamerAdapter: StreamerAdapter
    private lateinit var recommendStreamAdapter : RecommendStreamContainerAdapter
    private lateinit var concatAdapter : ConcatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_streamer,container,false)
        streamerAdapter = StreamerAdapter(viewModel)
        recommendStreamAdapter = RecommendStreamContainerAdapter(viewModel,viewModel)
        concatAdapter = ConcatAdapter().apply {
            addAdapter(streamerAdapter)
            addAdapter(recommendStreamAdapter)
        }
        viewDataBinding = FragmentStreamerBinding.bind(root).apply {
            streamRecyclerview.adapter = concatAdapter
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchBookmarkedStreamData()
        setFragmentResultListener(streamRequestKey) { requestKey, bundle ->
            viewModel.fetchBookmarkedStreamData()
        }
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.streamDataList.collect{ streamDataList ->
                        streamerAdapter.submitList(streamDataList)
                    }
                }
                launch {
                    viewModel.recommendStreamDataList.collect{ recommendStreamDataList ->
                        if(recommendStreamDataList.isNotEmpty()){
                            recommendStreamAdapter.submitList(RecommendStreamContainerObject.getSingleObject(itemList = recommendStreamDataList))
                        }
                    }
                }
                launch {
                    viewModel.navigateTwitchDeepLink.collect{ deepLink ->
                        requireContext().handleTwitchDeepLink(deepLink)
                    }
                }
                launch {
                    viewModel.showMoreBottomSheetDialog.collect{ streamerId ->
                        val moreBottomSheetDialog = MoreBottomDialogFragment.getInstance(streamerId)
                        moreBottomSheetDialog.show(parentFragmentManager,MoreBottomDialogFragment.TAG)
                    }
                }
            }
        }
    }
}