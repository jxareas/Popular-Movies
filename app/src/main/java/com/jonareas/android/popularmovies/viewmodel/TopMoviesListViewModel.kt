package com.jonareas.android.popularmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.ContinuationInterceptor

@HiltViewModel
class TopMoviesListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) :
    ViewModel() {

    private var _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> = _popularMovies

    init {
        fetchTopMovies()
    }

    private fun fetchTopMovies() {

        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchPopularMovies()
                .map { listOfMovies ->
                    listOfMovies.filter { movie -> movie.voteAverage > 7.5 }
                        .sortedByDescending { movie -> movie.voteAverage }
                }.collectLatest { listOfMovies ->
                    _popularMovies.postValue(listOfMovies)
                }
        }

    }

}