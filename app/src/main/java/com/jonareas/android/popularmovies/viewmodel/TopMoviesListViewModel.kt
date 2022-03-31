package com.jonareas.android.popularmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.model.repository.MovieRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopMoviesListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dispatchers : DispatcherProvider
) :
    ViewModel() {

    private var _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>> = _topRatedMovies

    init {
        fetchTopRatedMovies()
    }

    private fun fetchTopRatedMovies() {

        viewModelScope.launch(dispatchers.io) {
            movieRepository.fetchPopularMovies()
                .map { listOfMovies ->
                    listOfMovies.filter { movie -> movie.voteAverage >= 7.5 }
                        .sortedByDescending { movie -> movie.voteAverage }
                }.collectLatest { listOfMovies ->
                    _topRatedMovies.postValue(listOfMovies)
                }
        }

    }

}