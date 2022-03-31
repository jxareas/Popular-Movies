package com.jonareas.android.popularmovies.di

import com.jonareas.android.popularmovies.utils.DefaultDispatchers
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatchers() : DispatcherProvider = DefaultDispatchers

}