package org.torproject.android.mini

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import org.torproject.android.service.OrbotConstants
import org.torproject.android.service.util.Prefs
import org.torproject.orbotcore.Languages
import org.torproject.orbotcore.LocaleHelper.onAttach
import java.util.*

class OrbotMiniApp : Application(), OrbotConstants {
    override fun onCreate() {
        super.onCreate()
        Languages.setup(MiniMainActivity::class.java, R.string.menu_settings)
        if (Prefs.getDefaultLocale() != Locale.getDefault().language) {
            Languages.setLanguage(this, Prefs.getDefaultLocale(), true)
        }
    }

    override fun attachBaseContext(base: Context) {
        Prefs.setContext(base)
        super.attachBaseContext(onAttach(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (Prefs.getDefaultLocale() != Locale.getDefault().language) Languages.setLanguage(this, Prefs.getDefaultLocale(), true)
    }
}