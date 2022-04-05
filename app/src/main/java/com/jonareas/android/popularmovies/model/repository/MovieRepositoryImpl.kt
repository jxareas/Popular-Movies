package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.network.MovieApi
import com.jonareas.android.popularmovies.network.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl private constructor(private val movieService: MovieService) : MovieRepository {

    companion object {
        @Volatile
        private var INSTANCE : MovieRepository? = null

        private fun createInstance() : MovieRepository =
            MovieRepositoryImpl(MovieApi.movieService)

        operator fun invoke() : MovieRepository =
            INSTANCE ?: synchronized(this) {
                createInstance()
            }.also { INSTANCE = it }


    }

    override suspend fun fetchPopularMovies(): Flow<List<Movie>> =
        flow {
            emit(movieService.fetchMovies().results)
        }
}
