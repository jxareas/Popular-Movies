package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.network.service.TvShowsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(private val tvShowsService: TvShowsService) :
    TvShowsRepository {

    override suspend fun fetchPopularShows(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchPopularTvShows().tvShows)
        }

    override suspend fun fetchTopRatedTvShows(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchTopRatedTvShows().tvShows)
        }

    override suspend fun fetchOnTheAirTvShows(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchOnTheAirTvShows().tvShows)
        }

    override suspend fun fetchAiringTodayTvShows(): Flow<List<TvShow>> =
        flow {
            emit(tvShowsService.fetchAiringTodayTvShows().tvShows)
        }


}