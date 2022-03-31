package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchPopularMovies() : Flow<List<Movie>>

}