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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

        private var _popularMovies = MutableLiveData<List<Movie>>()
        val popularMovies : LiveData<List<Movie>> = _popularMovies

        init {
            fetchMovies()
        }

        private fun fetchMovies() {
            viewModelScope.launch(Dispatchers.IO) {
                movieRepository.fetchPopularMovies().collectLatest { listOfMovies ->
                _popularMovies.postValue(listOfMovies)

                }
            }
        }

}