package com.jonareas.android.popularmovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.view.movies.*


class MoviesViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments : Set<Fragment> = setOf(PopularMoviesFragment(), NowPlayingMoviesFragment(), UpcomingMoviesFragment(), HotMoviesFragment(), TopRatedMoviesFragment())

    val titles : Set<Int> = setOf(R.string.popular, R.string.now_playing, R.string.upcoming, R.string.hot, R.string.top, R.string.settings)

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments.elementAt(position)


}
