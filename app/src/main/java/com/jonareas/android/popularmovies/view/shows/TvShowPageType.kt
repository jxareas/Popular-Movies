package com.jonareas.android.popularmovies.view.shows

import com.jonareas.android.popularmovies.viewmodel.base.TvShowBaseViewModel
import com.jonareas.android.popularmovies.viewmodel.shows.*
import kotlin.reflect.KClass

enum class TvShowPageType(val viewModelClass : KClass<out TvShowBaseViewModel>) {
    Popular(PopularTvShowsViewModel::class),
    AiringToday(AiringTodayTvShowsViewModel::class),
    OnTheAir(OnTheAirTvShowsViewModel::class),
    Hot(HotTvShowsViewModel::class),
    TopRated(TopRatedTvShowsViewModel::class),

}

