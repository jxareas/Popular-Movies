package com.jonareas.android.popularmovies.network.service

import com.jonareas.android.popularmovies.network.response.GetMovieResponse
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular")
    suspend fun fetchMovies() : GetMovieResponse


}