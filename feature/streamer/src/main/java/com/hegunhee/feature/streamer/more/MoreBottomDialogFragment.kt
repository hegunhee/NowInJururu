package com.hegunhee.feature.streamer.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.databinding.DialogMoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreBottomDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var viewDataBinding : DialogMoreBinding
    private val viewModel : MoreViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_more,container,false)
        arguments?.let {
            it.getString(streamerLoginBundleKey)?.let { streamerName ->
                viewModel.fetchData(streamerName)
            }
        }
        viewDataBinding = DialogMoreBinding.bind(root).apply {
            viewModel = this@MoreBottomDialogFragment.viewModel
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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