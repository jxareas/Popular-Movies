package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun fetchPopularTvShowsFlow() : Flow<List<TvShow>>

    suspend fun fetchTopRatedTvShowsFlow() : Flow<List<TvShow>>

    suspend fun fetchOnTheAirTvShowsFlow() : Flow<List<TvShow>>

    suspend fun fetchAiringTodayTvShowsFlow() : Flow<List<TvShow>>

}