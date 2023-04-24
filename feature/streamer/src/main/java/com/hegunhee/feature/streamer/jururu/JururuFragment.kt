package com.hegunhee.feature.streamer.jururu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.databinding.FragmentJururuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JururuFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentJururuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_jururu,container,false)
        viewDataBinding = FragmentJururuBinding.bind(root).apply {

        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}