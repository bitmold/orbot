package org.torproject.android;

import org.torproject.orbotcore.BaseSettingsActivity;

public class OrbotSettingsActivity extends BaseSettingsActivity {

    @Override
    protected int getPreferencesXmlResource() {
        return R.xml.preferences;
    }
}
