package com.jonareas.android.popularmovies.view.shows

import com.jonareas.android.popularmovies.viewmodel.base.TvShowBaseViewModel
import com.jonareas.android.popularmovies.viewmodel.shows.AiringTodayTvShowsViewModel
import com.jonareas.android.popularmovies.viewmodel.shows.OnTheAirTvShowsViewModel
import com.jonareas.android.popularmovies.viewmodel.shows.PopularTvShowsViewModel
import com.jonareas.android.popularmovies.viewmodel.shows.TopRatedTvShowsViewModel
import kotlin.reflect.KClass

enum class TvShowPageType(val viewModelClass : KClass<out TvShowBaseViewModel>) {
    Popular(PopularTvShowsViewModel::class),
    AiringToday(AiringTodayTvShowsViewModel::class),
    OnTheAir(OnTheAirTvShowsViewModel::class),
    TopRated(TopRatedTvShowsViewModel::class),

}

