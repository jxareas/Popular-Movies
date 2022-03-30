package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.network.response.GetMoviesResponse

interface MovieRepository {

    suspend fun fetchPopularMovies() : GetMoviesResponse

}