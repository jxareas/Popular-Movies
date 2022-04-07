package com.jonareas.android.popularmovies.viewmodel.shows

import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.model.repository.TvShowsRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.shows.TvShowPageType
import com.jonareas.android.popularmovies.viewmodel.base.TvShowBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//@HiltViewModel
//class HotTvShowsViewModel @Inject constructor(
//    private val tvShowsRepository: TvShowsRepository,
//    private val dispatchers: DispatcherProvider,
//) : TvShowBaseViewModel(dispatchers) {
//
//    override val pageType: TvShowPageType
//        get() = TvShowPageType.Hot
//
//    override suspend fun getTvShowsDataFlow(): Flow<List<TvShow>> =
//        tvShowsRepository.fetchPopularTvShowsFlow().map { listOfShows ->
//            listOfShows.filter { tvShow ->
//                tvShow.voteAverage >= 7.5 }.sortedByDescending(TvShow::voteAverage)
//        }
//
//}