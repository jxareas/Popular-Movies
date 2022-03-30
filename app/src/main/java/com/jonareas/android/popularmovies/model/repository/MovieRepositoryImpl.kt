package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.network.service.MovieService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override suspend fun fetchPopularMovies(): List<Movie> =
       try {
           movieService.fetchPopularMovies().movies
       }
       catch(throwable : Throwable) {
           throwable.printStackTrace()
           listOf()
       }
}