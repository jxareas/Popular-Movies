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
class HotMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dispatchers : DispatcherProvider
) :
    ViewModel() {

    private var _hotMovies = MutableLiveData<List<Movie>>()
    val hotMovies: LiveData<List<Movie>> = _hotMovies

    init {
        fetchTopRatedMovies()
    }

    private fun fetchTopRatedMovies() {

        viewModelScope.launch(dispatchers.io) {
            try {
                movieRepository.fetchPopularMovies()
                    .map { listOfMovies ->
                        listOfMovies.filter { movie -> movie.voteAverage >= 7.5 }
                            .sortedByDescending { movie -> movie.voteAverage }
                    }.collectLatest { listOfMovies ->
                        _hotMovies.postValue(listOfMovies)
                    }
            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }

    }

}