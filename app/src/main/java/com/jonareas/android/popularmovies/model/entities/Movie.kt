package com.jonareas.android.popularmovies.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName(value = "original_title")
    val title : String = "",
    @SerialName(value = "overview")
    val description : String = "",
    @SerialName(value = "poster_path")
    val poster : String = ""
)