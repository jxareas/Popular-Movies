package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Actor
import kotlinx.coroutines.flow.Flow

interface ActorRepository {

    suspend fun fetchPopularActors() : Flow<List<Actor>>

}