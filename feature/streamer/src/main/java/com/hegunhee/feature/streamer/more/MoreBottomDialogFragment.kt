package com.hegunhee.feature.streamer.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hegunhee.feature.common.fragmentResultKeys.streamRequestKey
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.databinding.DialogMoreBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreBottomDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var viewDataBinding : DialogMoreBinding
    private val viewModel : MoreViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(isArgumentEmpty()){
            Toast.makeText(requireContext(), getString(R.string.empty_streamer_argument), Toast.LENGTH_SHORT).show()
            dismissAllowingStateLoss()
        }
        arguments?.let {
            it.getString(streamerLoginBundleKey)?.let { streamerName ->
                viewModel.fetchData(streamerName)
            }
        }
        val root = inflater.inflate(R.layout.dialog_more,container,false)
        viewDataBinding = DialogMoreBinding.bind(root).apply {
            viewModel = this@MoreBottomDialogFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.isSuccessDeleteStreamer.collect{
                    parentFragmentManager.setFragmentResult(streamRequestKey,Bundle.EMPTY)
                    dismissAllowingStateLoss()
                }
            }
        }
    }

    private fun isArgumentEmpty() : Boolean {
        return arguments?.getString(streamerLoginBundleKey) == null
    }

    companion object{
        const val streamerLoginBundleKey = "StreamerLogin"
        const val TAG = "MoreBottomDialogFragment"
        fun getInstance(streamerLogin : String) : MoreBottomDialogFragment{
            val bundle = Bundle()
            bundle.putString(streamerLoginBundleKey,streamerLogin)
            return MoreBottomDialogFragment().apply {
                arguments = bundle
            }
        }
    }
}