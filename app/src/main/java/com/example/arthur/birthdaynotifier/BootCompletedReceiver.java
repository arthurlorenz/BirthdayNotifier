package com.example.arthur.birthdaynotifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by arthur on 25.04.16.
 */
public class BootCompletedReceiver extends BroadcastReceiver {

    Intent bnIntent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        bnIntent = new Intent(context, BirthdayNotifyService.class);
        pendingIntent = PendingIntent.getService(context, 0, bnIntent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, AlarmManager.INTERVAL_HOUR, pendingIntent);
    }
}
