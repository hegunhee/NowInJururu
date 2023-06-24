package com.hegunhee.feature.streamer.jururu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.feature.common.twitch.*
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.StreamerAdapter
import com.hegunhee.feature.streamer.databinding.FragmentJururuBinding
import com.hegunhee.feature.streamer.toStreamViewTypeData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JururuFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentJururuBinding
    private lateinit var streamerAdapter : StreamerAdapter
    private val viewModel : JururuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_jururu,container,false)
        streamerAdapter = StreamerAdapter(viewModel)
        viewDataBinding = FragmentJururuBinding.bind(root).apply {
            jururuRecyclerview.adapter = streamerAdapter
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getJururuStreamData()
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.jururuStreamData.collect{
                    streamerAdapter.submitList(listOf<StreamDataType>(it).toStreamViewTypeData())
                }
            }
            launch {
                viewModel.navigateStreamerTwitch.collect{ streamerLogin ->
                    requireContext().handleOpenTwitchApp(streamerLogin)
                }
            }
        }
    }
}