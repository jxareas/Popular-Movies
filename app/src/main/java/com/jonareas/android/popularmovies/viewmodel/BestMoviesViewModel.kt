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
class BestMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private var _bestMovies = MutableLiveData<List<Movie>>()
    val bestMovies: LiveData<List<Movie>> = _bestMovies


    init {
        fetchMovies()
    }

    private fun fetchMovies() {

        viewModelScope.launch(dispatchers.io) {
            try {
                movieRepository.fetchTopRatedMovies().collectLatest {
                        listOfMovies -> _bestMovies.postValue(listOfMovies) }
            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }

    }

}