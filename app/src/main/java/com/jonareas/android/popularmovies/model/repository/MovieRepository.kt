package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchPopularMovies() : Flow<List<Movie>>

    suspend fun fetchUpcomingMovies() : Flow<List<Movie>>

    suspend fun fetchNowPlayingMovies() : Flow<List<Movie>>

    suspend fun fetchMovieById(id : Int) : Flow<Movie>

    suspend fun fetchTopRatedMovies() : Flow<List<Movie>>

}
