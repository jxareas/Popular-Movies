package com.jonareas.android.popularmovies.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val adult: Boolean?,
    @SerialName(value = "backdrop_path")
    val backdropPath: String?,
    @SerialName(value = "genre_ids")
    val genreIds: List<Int>?,
    val id: Int?,
    @SerialName(value = "original_language")
    val originalLanguage: String?,
    @SerialName(value = "original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerialName(value = "poster_path")
    val posterPath: String?,
    @SerialName(value = "release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @SerialName(value = "vote_average")
    val voteAverage: Float?,
    @SerialName(value = "vote_count")
    val voteCount: Int?
)

