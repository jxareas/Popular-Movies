package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.network.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override suspend fun fetchPopularMovies(): Flow<List<Movie>> =
        try {
            flow { emit(movieService.fetchPopularMovies().movies) }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            emptyFlow()
        }

    override suspend fun fetchMovieById(id : Int): Flow<Movie> =
        try {
            flow { emit(movieService.fetchMovieById(id)) }
        } catch(throwable : Throwable) {
            throwable.printStackTrace()
            emptyFlow()
        }
}
