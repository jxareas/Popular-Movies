package com.jonareas.android.popularmovies.view.actors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jonareas.android.popularmovies.adapter.ActorAdapter
import com.jonareas.android.popularmovies.databinding.FragmentMovieListBinding
import com.jonareas.android.popularmovies.viewmodel.actors.PopularActorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularActorsFragment : Fragment() {

    private val viewModel: PopularActorsViewModel by viewModels()

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

        viewModel.popularActors.observe(viewLifecycleOwner) { listOfActors ->
            listOfActors?.let {
                (recyclerViewMovies.adapter as ActorAdapter).submitList(it)
            }
        }

    }

    private fun setupRecyclerView(): Unit = binding.recyclerViewMovies.run {
        layoutManager = GridLayoutManager(activity, 2)
        adapter = ActorAdapter {

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}