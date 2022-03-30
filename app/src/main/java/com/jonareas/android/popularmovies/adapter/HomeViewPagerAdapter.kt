package com.jonareas.android.popularmovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.android.popularmovies.view.about.AboutFragment
import com.jonareas.android.popularmovies.view.movies.MovieListFragment

class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments : Set<Fragment> = setOf(MovieListFragment(), AboutFragment())

    val titles : Set<String> = setOf("Popular Movies", "About")

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments.elementAt(position)


}