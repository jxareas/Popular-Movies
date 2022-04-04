package com.jonareas.android.popularmovies.viewmodel.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.model.repository.TvShowsRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotTvShowsViewModel @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private var _popularShows = MutableLiveData<List<TvShow>>()
    val popularShows: LiveData<List<TvShow>> = _popularShows

    init {
        fetchPopularTvShows()
    }

    private fun fetchPopularTvShows() {

        viewModelScope.launch(dispatchers.io) {
            try {
                tvShowsRepository.fetchPopularShows()
                    .map { listOfShows ->
                        listOfShows.filter { show -> show.voteAverage >= 7.5 }
                            .sortedByDescending { movie -> movie.voteAverage }
                    }.collectLatest {
                        listOfShows -> _popularShows.postValue(listOfShows) }
            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }

    }


}