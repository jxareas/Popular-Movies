package com.jonareas.android.popularmovies.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jonareas.android.popularmovies.adapter.MediaListAdapter
import com.jonareas.android.popularmovies.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviePageFragment() : Fragment() {

    private val moviePageType: MoviePageType by lazy {
        MoviePageType.values().first {
            it.ordinal == arguments?.getInt(MOVIE_PAGE_TYPE)
        }
    }

    companion object {
        const val MOVIE_PAGE_TYPE: String = "movie_page_type"

        fun newInstance(type: MoviePageType): MoviePageFragment {
            val myFragment = MoviePageFragment()

            val args = Bundle()
            args.putInt(MOVIE_PAGE_TYPE, type.ordinal)
            myFragment.arguments = args

            return myFragment
        }
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[moviePageType.viewModelClass.java]
    }
    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initObservers()
    }

    private fun initObservers(): Unit = binding.run {
        viewModel.movies.observe(viewLifecycleOwner) { listOfMovies ->
            listOfMovies.let {
                (recyclerViewMovies.adapter as MediaListAdapter).submitList(it)
            }
        }

    }

    private fun setupRecyclerView(): Unit = binding.recyclerViewMovies.run {
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = MediaListAdapter()
    }

}