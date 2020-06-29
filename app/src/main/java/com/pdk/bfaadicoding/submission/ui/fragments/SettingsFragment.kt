package com.pdk.bfaadicoding.submission.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.pdk.bfaadicoding.submission.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
    }
}