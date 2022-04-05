package com.jonareas.android.popularmovies.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Media(
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

@Serializable
data class Movie(
    val adult: Boolean?,
    @SerialName(value = "backdrop_path")
    val backdropPath: String?,
    @SerialName(value = "genre_ids")
    val genreIds: List<Int>? = listOf(),
    @SerialName(value = "original_language")
    val originalLanguage: String?,
    @SerialName(value = "original_title")
    val originalTitle: String?,
    val popularity: Double?,
    @SerialName(value = "release_date")
    val releaseDate: String?,
    val video: Boolean?,
    @SerialName(value = "vote_count")
    val voteCount: Int?
) : Media()

@Serializable
data class TvShow(
    @SerialName(value = "backdrop_path")
    val backdropPath: String = "",
    @SerialName(value = "first_air_date")
    val firstAirDate: String,
    @SerialName(value = "genre_ids")
    val genreIds: List<Int>,
    @SerialName(value = "origin_country")
    val originCountry: List<String>,
    @SerialName(value = "original_language")
    val originalLanguage: String,
    @SerialName(value = "original_name")
    val originalName: String,
    val popularity: Double,
    @SerialName(value = "vote_count")
    val voteCount: Int
) : Media()
