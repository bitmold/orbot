package org.torproject.android.mini

import org.torproject.orbotcore.BaseSettingsActivity

class OrbotMiniSettingsActivity : BaseSettingsActivity() {
    override val preferencesXmlResource: Int
        protected get() = R.xml.preferences
}