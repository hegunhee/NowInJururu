package com.hegunhee.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.hegunhee.feature.common.fragmentResultKeys.streamRequestKey
import com.hegunhee.feature.common.lottie.LottieDialog
import com.hegunhee.feature.common.twitch.handleTwitchDeepLink
import com.hegunhee.feature.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(){

    private lateinit var viewDataBinding : FragmentSearchBinding
    private val viewModel : SearchViewModel by viewModels()
    private lateinit var searchAdapter : SearchAdapter
    private val lottieDialog = LottieDialog()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search,container,false)
        searchAdapter = SearchAdapter(viewModel)
        viewDataBinding = FragmentSearchBinding.bind(root).apply {
            viewModel = this@SearchFragment.viewModel
            this.searchResultRecyclerView.adapter = searchAdapter
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onClickSearchButton()
                observePagingData()
                true
            } else {
                false
            }
        }
        observeSearchState()
        observeData()
        observePagingData()
    }

    private fun observeSearchState() {
        searchAdapter.addLoadStateListener {
            when(val loadState = it.refresh) {
                is LoadState.Loading -> {
                    if(searchAdapter.snapshot().isEmpty()) {
                        lottieDialog.show(parentFragmentManager,LottieDialog.TAG)
                    }
                }
                is LoadState.NotLoading -> {
                    lottieDialog.dismissAllowingStateLoss()
                }
                is LoadState.Error -> {

                }
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.navigateStreamerTwitch.collect{ deepLink ->
                        requireContext().handleTwitchDeepLink(deepLink)
                    }
                }
                launch{
                    viewModel.toastMessage.collect{ message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
                launch {
                    viewModel.uiEvent.collect {
                        when (it) {
                            is SearchUiEvent.Refresh -> {
                                searchAdapter.refresh()
                                callRefreshStreamData()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observePagingData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.searchResult.collectLatest {
                        searchAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun callRefreshStreamData() {
        setFragmentResult(streamRequestKey, Bundle.EMPTY)
    }
}