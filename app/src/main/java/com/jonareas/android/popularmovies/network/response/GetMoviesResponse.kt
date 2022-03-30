package com.jonareas.android.popularmovies.network.response

import com.jonareas.android.popularmovies.model.entities.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMoviesResponse(
    val page: Int?,
    @SerialName(value = "results")
    val movies: List<Movie> = listOf(),
    @SerialName(value = "total_pages")
    val totalPages: Int?,
    @SerialName(value = "total_results")
    val totalResults: Int?

)