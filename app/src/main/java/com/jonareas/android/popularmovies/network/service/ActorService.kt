package com.jonareas.android.popularmovies.network.service

import com.jonareas.android.popularmovies.network.response.GetActorsResponse
import retrofit2.http.GET

interface ActorService {

    @GET(value = "person/popular")
    suspend fun fetchPopularPeople() : GetActorsResponse

}