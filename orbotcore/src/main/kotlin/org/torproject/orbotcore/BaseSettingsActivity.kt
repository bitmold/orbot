/* Copyright (c) 2009, Nathan Freitas, Orbot / The Guardian Project - http://openideals.com/guardian */ /* See LICENSE for licensing information */
package org.torproject.orbotcore

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.*
import android.preference.Preference.OnPreferenceChangeListener
import android.view.inputmethod.EditorInfo
import androidx.annotation.XmlRes
import org.torproject.orbotcore.LocaleHelper.onAttach

abstract class BaseSettingsActivity : PreferenceActivity() {
    private var prefLocale: ListPreference? = null

    @get:XmlRes
    protected abstract val preferencesXmlResource: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(preferencesXmlResource)
        setNoPersonalizedLearningOnEditTextPreferences()
        preferenceManager.sharedPreferencesMode = Context.MODE_MULTI_PROCESS
        prefLocale = findPreference("pref_default_locale") as ListPreference
        val languages = Languages[this]
        prefLocale?.entries = languages.allNames
        prefLocale?.entryValues = languages.getSupportedLocales()
        prefLocale?.onPreferenceChangeListener = OnPreferenceChangeListener { _: Preference?, newValue: Any? ->
            val language = newValue as String?
            val intentResult = Intent()
            intentResult.putExtra("locale", language)
            setResult(Activity.RESULT_OK, intentResult)
            finish()
            false
        }
    }

    override fun attachBaseContext(base: Context) = super.attachBaseContext(onAttach(base))

    private fun setNoPersonalizedLearningOnEditTextPreferences() {
        val preferenceScreen = preferenceScreen
        val categoryCount = preferenceScreen.preferenceCount
        for (i in 0 until categoryCount) {
            var p = preferenceScreen.getPreference(i)
            if (p is PreferenceCategory) {
                val pc = p
                val preferenceCount = pc.preferenceCount
                for (j in 0 until preferenceCount) {
                    p = pc.getPreference(j)
                    if (p is EditTextPreference) {
                        val editText = p.editText
                        editText.imeOptions = editText.imeOptions or EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING
                    }
                }
            }
        }
    }
}