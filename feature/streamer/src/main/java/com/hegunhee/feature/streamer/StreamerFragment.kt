package com.hegunhee.feature.streamer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hegunhee.feature.common.fragmentResultKeys.streamRequestKey
import com.hegunhee.feature.common.twitch.handleOpenTwitchApp
import com.hegunhee.feature.streamer.databinding.FragmentStreamerBinding
import com.hegunhee.feature.streamer.more.MoreBottomDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StreamerFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentStreamerBinding
    private val viewModel : StreamerViewModel by viewModels()
    private lateinit var streamerAdapter: StreamerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_streamer,container,false)
        streamerAdapter = StreamerAdapter(viewModel)
        viewDataBinding = FragmentStreamerBinding.bind(root).apply {
            streamerRecyclerview.adapter = streamerAdapter
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
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.streamDataList.collect{ streamDataList ->
                    streamerAdapter.submitList(streamDataList)
                }
            }
            launch {
                viewModel.navigateStreamerTwitch.collect{ streamerLogin ->
                    requireContext().handleOpenTwitchApp(streamerLogin)
                }
            }
            launch {
                viewModel.showMoreBottomSheetDialog.collect{ streamerLogin ->
                    val dialog = MoreBottomDialogFragment().apply {
                        val bundle = Bundle()
                        bundle.putString("streamerLogin",streamerLogin)
                        arguments = bundle
                    }
                    dialog.show(parentFragmentManager,"moreDialog")
                }
            }
        }
    }
}