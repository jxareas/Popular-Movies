package com.jonareas.android.popularmovies.viewmodel.movies

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
class UpcomingMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dispatchers : DispatcherProvider
) : ViewModel() {

    private var _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>> = _upcomingMovies

    init {
        fetchUpcomingMovies()
    }

    private fun fetchUpcomingMovies() {

        viewModelScope.launch(dispatchers.io) {
            try {
                movieRepository.fetchUpcomingMovies()
                    .map { listOfMovies ->
                    listOfMovies.sortedBy { movie -> movie.voteAverage }
                    }
                    .collectLatest {
                        listOfMovies -> _upcomingMovies.postValue(listOfMovies) }
            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }

    }


}