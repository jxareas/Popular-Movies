package com.jonareas.android.popularmovies.view.movies

import com.jonareas.android.popularmovies.viewmodel.base.MovieBaseViewModel
import com.jonareas.android.popularmovies.viewmodel.movies.NowPlayingMoviesViewModel
import com.jonareas.android.popularmovies.viewmodel.movies.PopularMoviesViewModel
import com.jonareas.android.popularmovies.viewmodel.movies.TopRatedMoviesViewModel
import com.jonareas.android.popularmovies.viewmodel.movies.UpcomingMoviesViewModel
import kotlin.reflect.KClass

enum class MoviePageType(val viewModelClass: KClass<out MovieBaseViewModel>) {
    Popular(PopularMoviesViewModel::class),
    NowPlaying(NowPlayingMoviesViewModel::class),
    ComingSoon(UpcomingMoviesViewModel::class),
    TopRated(TopRatedMoviesViewModel::class)
}