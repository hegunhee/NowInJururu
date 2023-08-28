package com.hegunhee.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hegunhee.feature.common.fragmentResultKeys.streamRequestKey
import com.hegunhee.feature.common.lottie.LottieDialog
import com.hegunhee.feature.common.twitch.handleTwitchDeepLink
import com.hegunhee.feature.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
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
        observeData()

        viewDataBinding.searchEditText.setOnEditorActionListener { editText, actionId, event ->
            return@setOnEditorActionListener if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onClickSearchButton()
                true
            } else {
                false
            }

        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.searchResult.collect{
                    searchAdapter.submitList(it)
                }
            }
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
                viewModel.isBookMarkSuccess.collect{
                    callRefreshStreamData()
                }
            }
            launch {
                viewModel.isLoading.collect { isLoading ->
                    if(isLoading){
                        lottieDialog.show(parentFragmentManager,LottieDialog.TAG)
                    }else{
                        lottieDialog.dismissAllowingStateLoss()
                    }
                }
            }
        }
    }

    private fun callRefreshStreamData() {
        setFragmentResult(streamRequestKey, Bundle.EMPTY)
    }
}