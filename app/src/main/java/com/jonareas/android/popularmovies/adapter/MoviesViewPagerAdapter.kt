package com.jonareas.android.popularmovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.view.movies.MoviePageFragment
import com.jonareas.android.popularmovies.view.movies.MoviePageType


class MoviesViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val pages: Set<MoviePageType> = setOf(MoviePageType.Popular, MoviePageType.NowPlaying, MoviePageType.ComingSoon, MoviePageType.TopRated)

    val titles: Set<Int> = setOf(R.string.popular, R.string.now_playing, R.string.coming_soon, R.string.top_rated)

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment =
        MoviePageFragment.newInstance(pages.elementAt(position))


}
