package com.jonareas.android.popularmovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.view.shows.*

class TvShowsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: Set<Fragment> = setOf(PopularTvShowsFragment(),
        AiringTodayTvShowsFragment(),
        OnTheAirTvShowsFragment(),
        HotTvShowsFragment(),
        TopRatedTvShowsFragment())
    val titles: Set<Int> =
        setOf(R.string.popular,
            R.string.airing_today,
            R.string.on_the_air,
            R.string.hot,
            R.string.top_rated)


    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments.elementAt(position)


}