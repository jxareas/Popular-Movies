package com.jonareas.android.popularmovies.view.movies

import com.jonareas.android.popularmovies.viewmodel.base.MovieBaseViewModel
import com.jonareas.android.popularmovies.viewmodel.movies.*
import kotlin.reflect.KClass

enum class MoviePageType(val viewModelClass: KClass<out MovieBaseViewModel>) {
    Popular(PopularMoviesViewModel::class),
    NowPlaying(NowPlayingMoviesViewModel::class),
    ComingSoon(UpcomingMoviesViewModel::class),
    Hot(HotMoviesViewModel::class),
    TopRated(TopRatedMoviesViewModel::class)
}