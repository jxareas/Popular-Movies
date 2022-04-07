package com.jonareas.android.popularmovies.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.movies.ApiLoadingState
import com.jonareas.android.popularmovies.view.shows.TvShowPageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class TvShowBaseViewModel constructor(private val dispatchers: DispatcherProvider) :
    ViewModel() {

    private var _tvShows = MutableLiveData<List<TvShow>>()
    val tvShows: LiveData<List<TvShow>> = _tvShows

    private var _showLoadingState = MutableLiveData<ApiLoadingState>()
    val tvShowLoadingState : LiveData<ApiLoadingState> = _showLoadingState

    abstract val pageType: TvShowPageType

    init {
        fetchTvShows()
    }

    fun onFragmentReady() {
        fetchTvShows()
    }

    private fun fetchTvShows() {

        viewModelScope.launch(dispatchers.io) {
            try {

                _showLoadingState.postValue(ApiLoadingState.LOADING)

                getTvShowsDataFlow().collectLatest { listOfMovies ->
                    _tvShows.postValue(listOfMovies)
                }

                _showLoadingState.postValue(ApiLoadingState.DONE)
            } catch (throwable: Throwable) {
                _showLoadingState.postValue(ApiLoadingState.ERROR)
                throwable.printStackTrace()
            }
        }

    }

    abstract suspend fun getTvShowsDataFlow(): Flow<List<TvShow>>

}