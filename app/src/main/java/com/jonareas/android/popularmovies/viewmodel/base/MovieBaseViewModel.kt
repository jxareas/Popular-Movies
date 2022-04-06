package com.jonareas.android.popularmovies.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.movies.MoviePageType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class MovieBaseViewModel constructor(private val dispatchers: DispatcherProvider) :
    ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    abstract val pageType: MoviePageType

    init {
        fetchMovieList()
    }

    private fun fetchMovieList() {

        viewModelScope.launch(dispatchers.io) {
            try {
                getMovieListDataFlow().collectLatest { listOfMovies ->
                    _movies.postValue(listOfMovies)
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

    }

    abstract suspend fun getMovieListDataFlow(): Flow<List<Movie>>

}