package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.network.service.TvShowsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(private val tvShowsService: TvShowsService) :
    TvShowsRepository {

    override suspend fun fetchPopularTvShowsFlow(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchPopularTvShows().tvShows)
        }

    override suspend fun fetchTopRatedTvShowsFlow(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchTopRatedTvShows().tvShows)
        }

    override suspend fun fetchOnTheAirTvShowsFlow(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchOnTheAirTvShows().tvShows)
        }

    override suspend fun fetchAiringTodayTvShowsFlow(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchAiringTodayTvShows().tvShows)
        }


}