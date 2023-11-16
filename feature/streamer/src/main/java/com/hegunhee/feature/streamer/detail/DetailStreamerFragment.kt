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
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.databinding.FragmentDetailStreamerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailStreamerFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentDetailStreamerBinding
    private val viewModel : DetailStreamerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireStreamerIdArgument(arguments)
        val root = inflater.inflate(R.layout.fragment_detail_streamer,container,false)
        viewDataBinding = FragmentDetailStreamerBinding.bind(root).apply {
            viewModel = this@DetailStreamerFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
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
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.streamerData.collect {
                        it
                    }
                }
            }
        }
    }

}