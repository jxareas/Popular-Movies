package com.jonareas.android.popularmovies.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Media(
    val id: Int = 0,
    @SerialName(value = "title")
    val movieTitle: String = "",
    @SerialName(value = "name")
    val showTitle : String = "",
    @SerialName(value = "overview")
    val overview: String? = "",
    @SerialName(value = "vote_average")
    val voteAverage: Float = 0.0f,
    @SerialName(value = "poster_path")
    val posterPath: String? = "",
)