package com.jonareas.android.popularmovies.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class Actor(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String
)