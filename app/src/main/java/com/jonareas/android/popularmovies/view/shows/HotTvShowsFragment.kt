package com.jonareas.android.popularmovies.view.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jonareas.android.popularmovies.adapter.MovieAdapter
import com.jonareas.android.popularmovies.databinding.FragmentMovieListBinding
import com.jonareas.android.popularmovies.viewmodel.shows.HotTvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotTvShowsFragment : Fragment() {

    private val viewModel : HotTvShowsViewModel by viewModels()

    private var _binding : FragmentMovieListBinding? = null
    private val binding : FragmentMovieListBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initObservers()

    }

    private fun initObservers() : Unit = binding.run {

        viewModel.popularShows.observe(viewLifecycleOwner) { listOfTvShows ->
            listOfTvShows?.let {
                (recyclerViewMovies.adapter as MovieAdapter).itemList = it
            }
        }

    }

    private fun setupRecyclerView() : Unit = binding.recyclerViewMovies.run {
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = MovieAdapter()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}