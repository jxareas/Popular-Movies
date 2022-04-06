package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchPopularMoviesFlow() : Flow<List<Movie>>

    suspend fun fetchComingSoonMoviesFlow() : Flow<List<Movie>>

    suspend fun fetchNowPlayingMoviesFlow() : Flow<List<Movie>>

    suspend fun fetchMovieByIdFlow(id : Int) : Flow<Movie>

    suspend fun fetchTopRatedMoviesFlow() : Flow<List<Movie>>

}
