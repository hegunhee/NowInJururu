package com.hegunhee.feature.streamer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hegunhee.feature.streamer.databinding.FragmentStreamerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StreamerFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentStreamerBinding
    private val viewModel : StreamerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_streamer,container,false)
        viewDataBinding = FragmentStreamerBinding.bind(root).apply {

        }
        return root
    }
}