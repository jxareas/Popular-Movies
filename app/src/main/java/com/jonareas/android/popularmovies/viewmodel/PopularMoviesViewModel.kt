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
class PopularMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dispatchers : DispatcherProvider
) :
    ViewModel() {

    private var _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> = _popularMovies

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {

        viewModelScope.launch(dispatchers.io) {
          try {
              movieRepository.fetchPopularMovies().collectLatest {
                      listOfMovies -> _popularMovies.postValue(listOfMovies) }
          } catch(throwable : Throwable) {
              throwable.printStackTrace()
          }
        }

    }

}