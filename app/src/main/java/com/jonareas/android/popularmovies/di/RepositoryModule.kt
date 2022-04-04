package com.jonareas.android.popularmovies.di

import com.jonareas.android.popularmovies.model.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl) : MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun bindTvShowRepository(tvShowsRepositoryImpl: TvShowsRepositoryImpl) : TvShowsRepository

    @Binds
    @ViewModelScoped
    abstract fun bindActorRepository(actorRepositoryImpl : ActorRepositoryImpl) : ActorRepository

}