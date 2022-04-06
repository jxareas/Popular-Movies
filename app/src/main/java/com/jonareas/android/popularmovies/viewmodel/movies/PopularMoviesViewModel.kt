package com.jonareas.android.popularmovies.viewmodel.movies

import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.model.repository.MovieRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.movies.MoviePageType
import com.jonareas.android.popularmovies.viewmodel.base.MovieBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    dispatchers : DispatcherProvider
) : MovieBaseViewModel(dispatchers) {

    override val pageType: MoviePageType
        get() = MoviePageType.Popular

    override suspend fun getMovieListDataFlow(): Flow<List<Movie>> =
        movieRepository.fetchPopularMoviesFlow()

}