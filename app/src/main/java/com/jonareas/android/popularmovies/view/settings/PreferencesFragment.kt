package com.jonareas.android.popularmovies.view.settings

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.utils.*
import com.jonareas.android.popularmovies.view.about.AboutFragment


class PreferencesFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preferences, rootKey)
        setHasOptionsMenu(true)
        findPreference<EditTextPreference>(API_PREFERENCE_KEY)?.apply {
            summary = TMDb_API_KEY
        }

        findPreference<ListPreference>(THEME_PREFERENCE_KEY)?.setOnPreferenceChangeListener { _, newValue ->
            AppCompatDelegate.setDefaultNightMode(
                when (newValue) {
                    THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                    THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
            )
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.item_about -> {
                AboutFragment().show(childFragmentManager, "ABOUT_FRAGMENT")
                true
            }
            else -> false
        }



}
