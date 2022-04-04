package com.jonareas.android.popularmovies.network.response

import com.jonareas.android.popularmovies.model.entities.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetActorsResponse(
    val page: Int,
    @SerialName(value = "results")
    val actors: List<Actor>,
    val total_pages: Int,
    val total_results: Int
)