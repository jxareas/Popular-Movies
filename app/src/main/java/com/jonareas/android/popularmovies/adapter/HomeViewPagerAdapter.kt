package com.jonareas.android.popularmovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.android.popularmovies.view.movies.MovieListFragment
import com.jonareas.android.popularmovies.view.movies.TopMoviesListFragment
import com.jonareas.android.popularmovies.view.settings.PreferencesFragment

class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments : Set<Fragment> = setOf(MovieListFragment(), TopMoviesListFragment(), PreferencesFragment())

    val titles : Set<String> = setOf("New", "Top",  "Settings")

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments.elementAt(position)


}
