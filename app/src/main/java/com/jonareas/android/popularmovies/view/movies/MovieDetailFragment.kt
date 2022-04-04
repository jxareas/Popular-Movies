package com.jonareas.android.popularmovies.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jonareas.android.popularmovies.databinding.FragmentMovieDetailBinding
import com.jonareas.android.popularmovies.viewmodel.movies.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private val navArgs: MovieDetailFragmentArgs by navArgs()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovie(navArgs.movieId)
        initObservers()
    }

    private fun initObservers(): Unit = binding.run {

            lifecycleOwner = this@MovieDetailFragment.viewLifecycleOwner

            viewModel.movie.observe(viewLifecycleOwner) { movie ->
                binding.movie = movie
            }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
