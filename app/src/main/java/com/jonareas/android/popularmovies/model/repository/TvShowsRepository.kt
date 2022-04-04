package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun fetchPopularShows() : Flow<List<TvShow>>

    suspend fun fetchTopRatedTvShows() : Flow<List<TvShow>>

    suspend fun fetchOnTheAirTvShows() : Flow<List<TvShow>>

    suspend fun fetchAiringTodayTvShows() : Flow<List<TvShow>>

}