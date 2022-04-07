package com.jonareas.android.popularmovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.view.shows.TvShowPageFragment
import com.jonareas.android.popularmovies.view.shows.TvShowPageType

class TvShowsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    private val pages: Set<TvShowPageType> = setOf(TvShowPageType.Popular,
        TvShowPageType.AiringToday, TvShowPageType.OnTheAir, TvShowPageType.TopRated)

    val titles: Set<Int> = setOf(R.string.popular, R.string.airing_today, R.string.on_the_air, R.string.top_rated)

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment =
        TvShowPageFragment.newInstance(pages.elementAt(position))



}