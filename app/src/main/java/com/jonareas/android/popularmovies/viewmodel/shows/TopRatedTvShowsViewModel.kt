package com.jonareas.android.popularmovies.viewmodel.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.model.repository.TvShowsRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.shows.TvShowPageType
import com.jonareas.android.popularmovies.viewmodel.base.TvShowBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedTvShowsViewModel @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
    private val dispatchers: DispatcherProvider,
) : TvShowBaseViewModel(dispatchers) {

    override val pageType: TvShowPageType
        get() = TvShowPageType.TopRated

    override suspend fun getTvShowsDataFlow(): Flow<List<TvShow>> =
        tvShowsRepository.fetchTopRatedTvShowsFlow()

}