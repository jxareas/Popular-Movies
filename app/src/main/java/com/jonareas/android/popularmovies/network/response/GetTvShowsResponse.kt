package com.jonareas.android.popularmovies.network.response

import com.jonareas.android.popularmovies.model.entities.TvShow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTvShowsResponse(
    val page: Int,
    @SerialName(value = "results")
    val tvShows: List<TvShow> = listOf(),
    @SerialName(value = "total_pages")
    val totalPages: Int?,
    @SerialName(value = "total_results")
    val totalResults: Int?
)