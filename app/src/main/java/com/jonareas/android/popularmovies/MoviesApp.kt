package com.jonareas.android.popularmovies

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.jonareas.android.popularmovies.utils.THEME_DARK
import com.jonareas.android.popularmovies.utils.THEME_DEFAULT
import com.jonareas.android.popularmovies.utils.THEME_LIGHT
import com.jonareas.android.popularmovies.utils.THEME_PREFERENCE_KEY
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val theme = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            .getString(THEME_PREFERENCE_KEY, THEME_DEFAULT)

        AppCompatDelegate.setDefaultNightMode(
            when (theme) {
                THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }


}
