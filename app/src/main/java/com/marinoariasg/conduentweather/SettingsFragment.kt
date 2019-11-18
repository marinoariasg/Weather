package com.marinoariasg.conduentweather

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment() : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_fragment, rootKey)
    }
}