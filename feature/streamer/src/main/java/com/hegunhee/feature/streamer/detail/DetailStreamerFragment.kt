package com.hegunhee.feature.streamer.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hegunhee.feature.streamer.R
import com.hegunhee.feature.streamer.databinding.FragmentDetailStreamerBinding

class DetailStreamerFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentDetailStreamerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = arguments?.getString("streamerId")
        id?.let{
            it
        }
        val root = inflater.inflate(R.layout.fragment_detail_streamer,container,false)
        viewDataBinding = FragmentDetailStreamerBinding.bind(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}