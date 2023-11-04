package com.hegunhee.nowinjururu.feature.jururu

import android.content.Intent
import android.net.Uri
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
import com.hegunhee.nowinjururu.core.navigation.twitch.handleTwitchDeepLink
import com.hegunhee.nowinjururu.core.designsystem.adapter.streamer.StreamerAdapter
import com.hegunhee.nowinjururu.feature.jururu.databinding.FragmentJururuBinding
import com.hegunhee.nowinjururu.feature.searchkakao.KakaoSearchAdapter
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
            jururuRecyclerview.adapter = concatAdapter
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.favoriteStreamData.collect{
                        streamerAdapter.submitList(listOf(it))
                    }
                }
                launch {
                    viewModel.navigateTwitchDeepLink.collect{ deepLink ->
                        requireContext().handleTwitchDeepLink(deepLink)
                    }
                }
                launch {
                    viewModel.kakaoSearchData.collectLatest {
                        searchAdapter.submitData(it)
                    }
                }
                launch {
                    viewModel.navigateDeepLink.collect{ url ->
                        requireContext().apply {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}