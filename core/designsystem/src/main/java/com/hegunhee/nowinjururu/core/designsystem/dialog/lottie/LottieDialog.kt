package com.hegunhee.nowinjururu.core.designsystem.dialog.lottie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.hegunhee.nowinjururu.core.designsystem.R
import com.hegunhee.nowinjururu.core.designsystem.databinding.DialogLottieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LottieDialog : DialogFragment() {
    private lateinit var viewDataBinding : DialogLottieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_lottie,container,false)
        viewDataBinding = DialogLottieBinding.bind(root)
        return root
    }

    companion object {
        val TAG = "LottieDialog"
    }
}