package com.jonareas.android.popularmovies.view.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.utils.*


class PreferencesFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preferences, rootKey)
        findPreference<EditTextPreference>(API_PREFERENCE_KEY)?.apply {
            isSelectable = false
            isCopyingEnabled = true
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


}
