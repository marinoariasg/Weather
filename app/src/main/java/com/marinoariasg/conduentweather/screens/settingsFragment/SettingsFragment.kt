package com.marinoariasg.conduentweather.screens.settingsFragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.marinoariasg.conduentweather.R


class SettingsFragment() : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_fragment, rootKey)
    }
}