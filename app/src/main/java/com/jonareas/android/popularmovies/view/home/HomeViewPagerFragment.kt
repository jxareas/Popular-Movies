package com.jonareas.android.popularmovies.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.jonareas.android.popularmovies.adapter.HomeViewPagerAdapter
import com.jonareas.android.popularmovies.databinding.FragmentHomeViewPagerBinding


class HomeViewPagerFragment : Fragment() {

    private var _binding : FragmentHomeViewPagerBinding? = null
    private val binding : FragmentHomeViewPagerBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerWithTabLayout()
    }

    private fun setupViewPagerWithTabLayout() : Unit = binding.run {
        val homeAdapter = HomeViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerHome.adapter = homeAdapter

        TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, index ->
            tab.text = homeAdapter.titles.elementAt(index)
        }.attach()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}