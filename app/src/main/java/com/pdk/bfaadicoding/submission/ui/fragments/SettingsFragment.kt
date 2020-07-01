package com.pdk.bfaadicoding.submission.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.pdk.bfaadicoding.submission.R
import com.pdk.bfaadicoding.submission.utils.ReminderReceiver

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener,
    Preference.OnPreferenceClickListener {
    private lateinit var reminderPreference: SwitchPreferenceCompat
    private lateinit var aboutPreference: Preference
    private lateinit var reminderReceiver: ReminderReceiver
    private lateinit var themePreference: ListPreference
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)

        reminderPreference =
            findPreference<SwitchPreferenceCompat>(resources.getString(R.string.reminder_key)) as SwitchPreferenceCompat
        themePreference =
            findPreference<ListPreference>(resources.getString(R.string.theme_key)) as ListPreference
        aboutPreference =
            findPreference<Preference>(resources.getString(R.string.about)) as Preference

        reminderReceiver = ReminderReceiver()
        reminderPreference.isChecked = reminderReceiver.isReminder(requireContext())

        reminderPreference.onPreferenceChangeListener = this
        themePreference.onPreferenceChangeListener = this
        aboutPreference.onPreferenceClickListener = this
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        when (preference) {
            themePreference -> setTheme(newValue as String)
            reminderPreference -> reminderReceiver.setReminder(requireContext())
        }
        return true
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when (preference) {
            aboutPreference -> {
                findNavController().navigate(SettingsFragmentDirections.settingsToAbout())
            }
        }
        return true
    }

    private fun setTheme(mode: String) {
        when (mode) {
            resources.getStringArray(R.array.themeEntryArray)[0] -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            resources.getStringArray(R.array.themeEntryArray)[1] -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
}