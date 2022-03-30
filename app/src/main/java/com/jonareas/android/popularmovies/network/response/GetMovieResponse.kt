package com.jonareas.android.popularmovies.network.response

import com.jonareas.android.popularmovies.model.entities.Movie

data class GetMovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)