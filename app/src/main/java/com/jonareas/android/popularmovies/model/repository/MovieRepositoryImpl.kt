package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.network.response.GetMoviesResponse
import com.jonareas.android.popularmovies.network.service.MovieService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override suspend fun fetchPopularMovies(): GetMoviesResponse =
        movieService.fetchPopularMovies()
}