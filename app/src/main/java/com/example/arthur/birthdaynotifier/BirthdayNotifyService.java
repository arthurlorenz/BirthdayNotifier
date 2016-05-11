package com.example.arthur.birthdaynotifier;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by arthur on 25.04.16.
 */
public class BirthdayNotifyService extends Service {

    private ArrayList<MyContact> arrayList;
    private static boolean started = false;

    public BirthdayNotifyService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            arrayList = BirthdayDataFactory.getContacts(this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startBNS();
        started = true;
        Log.d("Service: ", "started!");
        return START_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void startBNS() {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).hasBirthday()) {
                NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(arrayList.get(i).getName())
                        .setContentText(arrayList.get(i).getName() + " hat heute Geburtstag!");

                Intent resultIntent = new Intent(this, MainActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MainActivity.class);

                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                notification.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(arrayList.get(i).getID(), notification.build());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * returns if service is start
     * @return true if service is started
     */
    public static boolean isStarted() {
        return started;
    }


}
