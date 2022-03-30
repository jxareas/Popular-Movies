package com.jonareas.android.popularmovies.network.response

import com.jonareas.android.popularmovies.model.entities.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMoviesResponse(
    @SerialName(value = "results")
    val movies: List<Movie> = listOf())