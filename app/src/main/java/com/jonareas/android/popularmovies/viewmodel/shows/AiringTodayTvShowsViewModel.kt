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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiringTodayTvShowsViewModel @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private var _airingTodayTvShows = MutableLiveData<List<TvShow>>()
    val airingTodayTvShows: LiveData<List<TvShow>> = _airingTodayTvShows

    init {
        fetchPopularTvShows()
    }

    private fun fetchPopularTvShows() {

        viewModelScope.launch(dispatchers.io) {
            try {
                tvShowsRepository.fetchAiringTodayTvShows().collectLatest {
                        listOfShows -> _airingTodayTvShows.postValue(listOfShows) }
            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }

    }


}