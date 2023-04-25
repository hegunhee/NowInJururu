package com.hegunhee.feature.streamer.jururu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hegunhee.domain.model.StreamDataType
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.StreamerAdapter
import com.hegunhee.feature.streamer.databinding.FragmentJururuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JururuFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentJururuBinding
    private lateinit var streamerAdapter : StreamerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_jururu,container,false)
        streamerAdapter = StreamerAdapter()
        viewDataBinding = FragmentJururuBinding.bind(root).apply {
            JururuRecyclerView.adapter = streamerAdapter
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        streamerAdapter.submitList(listOf<StreamDataType>(StreamDataType.TestJururuLiveStreamInfo))
    }
}