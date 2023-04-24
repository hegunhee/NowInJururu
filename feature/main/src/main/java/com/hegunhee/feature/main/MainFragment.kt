package com.hegunhee.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hegunhee.feature.main.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var viewDataBinding : FragmentMainBinding
    private lateinit var viewPager : ViewPager2
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main,container,false)
        viewDataBinding = FragmentMainBinding.bind(root).apply {
        }
        viewPager = viewDataBinding.viewPager
        bottomNavigationView = viewDataBinding.bottomNavigation
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = MainFragmentStateAdapter(this,viewLifecycleOwner.lifecycle)
        viewPager.offscreenPageLimit = MainTab.values().size-1
        viewPager.isUserInputEnabled = false

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val pos = MainTab.getPosition(menuItem)
            viewPager.setCurrentItem(pos,false)
            return@setOnItemSelectedListener true
        }
    }
}