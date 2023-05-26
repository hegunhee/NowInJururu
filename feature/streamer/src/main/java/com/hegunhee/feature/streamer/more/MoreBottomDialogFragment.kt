package com.hegunhee.feature.streamer.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.databinding.DialogMoreBinding

class MoreBottomDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var viewDataBinding : DialogMoreBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_more,container,false)
        viewDataBinding = DialogMoreBinding.bind(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            it.getString("streamerLogin")?.let {
                viewDataBinding.unfollowText.text = it
            }
        }
    }
}