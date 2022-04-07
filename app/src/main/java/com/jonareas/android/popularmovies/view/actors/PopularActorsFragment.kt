package com.jonareas.android.popularmovies.view.actors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jonareas.android.popularmovies.adapter.ActorListAdapter
import com.jonareas.android.popularmovies.databinding.FragmentMovieListBinding
import com.jonareas.android.popularmovies.network.ConnectivityLiveData
import com.jonareas.android.popularmovies.view.movies.ApiLoadingState
import com.jonareas.android.popularmovies.viewmodel.actors.PopularActorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularActorsFragment : Fragment() {
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private val viewModel: PopularActorsViewModel by viewModels()

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        connectivityLiveData = ConnectivityLiveData(requireActivity().application)
        super.onCreate(savedInstanceState)
    }

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

        viewModel.popularActors.observe(viewLifecycleOwner) { listOfActors ->
            listOfActors?.let {
                (recyclerViewMovies.adapter as ActorListAdapter).submitList(it)
            }
        }

        connectivityLiveData.observe(viewLifecycleOwner) { isAvailable ->

            when (isAvailable) {
                true -> {
                    viewModel.onFragmentReady()
                }
                false -> {

                }
            }
        }

        viewModel.actorsLoadingState.observe(viewLifecycleOwner) { currentState ->
            onLoadingStateChanged(currentState)

        }

    }

    private fun setupRecyclerView(): Unit = binding.recyclerViewMovies.run {
        layoutManager = GridLayoutManager(activity, 2)
        adapter = ActorListAdapter {

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onLoadingStateChanged(state: ApiLoadingState): Unit = binding.run {
        when (state) {
            ApiLoadingState.LOADING -> {

                loadingProgressBar.visibility = View.VISIBLE
            }
            ApiLoadingState.DONE -> {
                loadingProgressBar.visibility = View.GONE
            }
            ApiLoadingState.ERROR -> {
                loadingProgressBar.visibility = View.GONE
            }
        }
    }
}
