package com.jonareas.android.popularmovies.network.service

import com.jonareas.android.popularmovies.network.response.GetTvShowsResponse
import retrofit2.http.GET

interface TvShowsService {

    @GET(value = "tv/popular")
    suspend fun fetchPopularTvShows() : GetTvShowsResponse

    @GET(value = "tv/top_rated")
    suspend fun fetchTopRatedTvShows() : GetTvShowsResponse

    @GET(value = "tv/on_the_air")
    suspend fun fetchOnTheAirTvShows() : GetTvShowsResponse

    @GET(value = "tv/airing_today")
    suspend fun fetchAiringTodayTvShows() : GetTvShowsResponse

}