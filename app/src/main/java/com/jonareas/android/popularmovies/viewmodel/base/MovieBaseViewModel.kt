package com.jonareas.android.popularmovies.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.movies.ApiLoadingState
import com.jonareas.android.popularmovies.view.movies.MoviePageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class MovieBaseViewModel constructor(private val dispatchers: DispatcherProvider) :
    ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private var _movieLoadingState = MutableLiveData<ApiLoadingState>()
    val movieLoadingState : LiveData<ApiLoadingState> = _movieLoadingState
    abstract val pageType: MoviePageType

    init {
        fetchMovieList()
    }

    fun onFragmentReady() {
        fetchMovieList()
    }

    private fun fetchMovieList() {

        viewModelScope.launch(dispatchers.io) {
            try {
                _movieLoadingState.postValue(ApiLoadingState.LOADING)

                getMovieListDataFlow().collectLatest { listOfMovies ->
                    _movies.postValue(listOfMovies)
                }

                _movieLoadingState.postValue(ApiLoadingState.DONE)

            } catch (throwable: Throwable) {
                _movieLoadingState.postValue(ApiLoadingState.ERROR)
                throwable.printStackTrace()
            }
        }

    }

    abstract suspend fun getMovieListDataFlow(): Flow<List<Movie>>

}