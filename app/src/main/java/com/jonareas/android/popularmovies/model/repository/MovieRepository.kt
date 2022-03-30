package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie

interface MovieRepository {

    suspend fun fetchPopularMovies() : List<Movie>

}