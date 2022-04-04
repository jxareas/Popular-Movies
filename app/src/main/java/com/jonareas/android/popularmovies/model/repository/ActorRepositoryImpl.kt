package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Actor
import com.jonareas.android.popularmovies.network.service.ActorService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActorRepositoryImpl @Inject constructor(private val actorService: ActorService) :
    ActorRepository {
    override suspend fun fetchPopularActors(): Flow<List<Actor>> =
        flow {
            emit(actorService.fetchPopularPeople().actors)
        }
}