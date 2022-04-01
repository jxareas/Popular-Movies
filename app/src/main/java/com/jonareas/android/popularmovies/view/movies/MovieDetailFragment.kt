package com.jonareas.android.popularmovies.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonareas.android.popularmovies.databinding.FragmentMovieDetailBinding
import com.jonareas.android.popularmovies.utils.POSTER_PATH_PREFIX
import com.jonareas.android.popularmovies.viewmodel.MovieDetailViewModel
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
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            viewModel.movie.observe(viewLifecycleOwner) { movie ->
                textViewTitle.text = movie.title
                textViewOverview.text = movie.overview
                Glide.with(root.context)
                    .setDefaultRequestOptions(RequestOptions())
                    .load("${POSTER_PATH_PREFIX}${movie.posterPath}")
                    .into(imageViewMoviePoster)

            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
