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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dispatchers: DispatcherProvider
) :
    ViewModel() {

    private var _movie = MutableLiveData<Movie>()
    val movie : LiveData<Movie> = _movie

    fun getMovie(id : Int) : Unit = fetchMovieById(id)

    private fun fetchMovieById(id : Int) {

        viewModelScope.launch(dispatchers.io) {
            movieRepository.fetchMovieById(id).collectLatest { selectedMovie ->
                _movie.postValue(selectedMovie)
            }
        }

    }

}
