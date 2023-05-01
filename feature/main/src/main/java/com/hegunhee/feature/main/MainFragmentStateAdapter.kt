package com.hegunhee.feature.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hegunhee.feature.streamer.StreamerFragment
import com.hegunhee.feature.streamer.jururu.JururuFragment
import com.hegunhee.feature.search.SearchFragment

enum class MainTab(
    val fragment :() -> Fragment,
    val menuRes : Int
){
    JURURU({ JururuFragment() }, R.id.jururu),
    STREAMER({ StreamerFragment() },R.id.streamer),
    SEARCH({ SearchFragment() },R.id.search);

    companion object {
        fun getFragment(position: Int) = values()[position].fragment.invoke()
        fun getPosition(menuItem: MenuItem) = values().first { it.menuRes == menuItem.itemId }.ordinal
    }
}
class MainFragmentStateAdapter(
    fragment : MainFragment,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragment.childFragmentManager,lifecycle){
    override fun createFragment(position: Int): Fragment = MainTab.getFragment(position)
    override fun getItemCount(): Int = MainTab.values().size
}