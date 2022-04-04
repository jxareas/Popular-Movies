package com.jonareas.android.popularmovies.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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