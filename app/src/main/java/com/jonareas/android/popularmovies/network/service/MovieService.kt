package com.jonareas.android.popularmovies.network.service

import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.network.response.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies() : GetMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieById(@Path("movie_id") movieId : Int) : Movie

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies() : GetMoviesResponse
}
