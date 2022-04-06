package com.jonareas.android.popularmovies.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.shows.TvShowPageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class TvShowBaseViewModel constructor(private val dispatchers: DispatcherProvider) :
    ViewModel() {

    private var _tvShows = MutableLiveData<List<TvShow>>()
    val tvShows: LiveData<List<TvShow>> = _tvShows

    abstract val pageType: TvShowPageType

    init {
        fetchTvShows()
    }

    private fun fetchTvShows() {

        viewModelScope.launch(dispatchers.io) {
            try {
                getTvShowsDataFlow().collectLatest { listOfMovies ->
                    _tvShows.postValue(listOfMovies)
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

    }

    abstract suspend fun getTvShowsDataFlow(): Flow<List<TvShow>>

}