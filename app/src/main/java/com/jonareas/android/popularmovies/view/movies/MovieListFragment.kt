package com.jonareas.android.popularmovies.view.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.databinding.FragmentMovieListBinding


class MovieListFragment : Fragment() {

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}