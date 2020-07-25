package org.torproject.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import androidx.core.app.NotificationCompat;
import org.torproject.android.service.OrbotConstants;
import org.torproject.android.service.util.Prefs;
import org.torproject.orbotcore.Languages;
import org.torproject.orbotcore.LocaleHelper;

import java.util.Locale;

public class OrbotApp extends Application implements OrbotConstants {

    @Override
    public void onCreate() {
        super.onCreate();
        Languages.setup(OrbotMainActivity.class, R.string.menu_settings);

        if (!Prefs.getDefaultLocale().equals(Locale.getDefault().getLanguage())) {
            Languages.setLanguage(this, Prefs.getDefaultLocale(), true);
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        Prefs.setContext(base);
        super.attachBaseContext(LocaleHelper.onAttach(base, Prefs.getDefaultLocale()));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (!Prefs.getDefaultLocale().equals(Locale.getDefault().getLanguage()))
            Languages.setLanguage(this, Prefs.getDefaultLocale(), true);
    }

    @SuppressLint("NewApi")
    protected void showToolbarNotification (String shortMsg, String notifyMsg, int notifyId, int icon)
    {

        NotificationCompat.Builder notifyBuilder;

        //Reusable code.
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(getPackageName());
        PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(getString(org.torproject.android.service.R.string.app_name));


        notifyBuilder.setContentIntent(pendIntent);

        notifyBuilder.setContentText(shortMsg);
        notifyBuilder.setSmallIcon(icon);
        notifyBuilder.setTicker(notifyMsg);

        notifyBuilder.setOngoing(false);

        notifyBuilder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText(notifyMsg).setBigContentTitle(getString(org.torproject.android.service.R.string.app_name)));

        Notification notification = notifyBuilder.build();

        notificationManager.notify(notifyId, notification);
    }
}
