package ppla01.foodo;

/**
 * Created by Gema Raditya on 4/11/2016..
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

public class NotifyService extends Service{
    Intent intent;
    String message;
    /**
     * Class for clients to access
     */
    public class ServiceBinder extends Binder {
        NotifyService getService() {
            return NotifyService.this;
        }
    }

    /*
    public void recieve(int a) {
        this.theCode =  a;
        Log.i("NotifyService", "cdde"+ this.theCode);

    } */

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "com.blundell.tut.service.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNM;

    @Override
    public void onCreate() {
        //Log.i("NotifyService", "onCreate()");
        //Log.i("NotifyService", "weleeeh"+ AlarmTask.code);

        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if(intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients
    private final IBinder mBinder = new ServiceBinder();

    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotification() {

        if (AlarmTask.code == 0) {
            message = "Good morning, It's time to take your Breakfast :)";
        }
        if(AlarmTask.code == 1) {
            message = "It's time for Lunch.. Don't miss it!";
        }
        if(AlarmTask.code == 2) {
            message = "Let's go for dinner!";
        }


        Intent resultIntent = new Intent(this, MenuActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.food_icon2)
                        .setContentTitle("Let's eat")
                        .setContentText(message);

        int mNotificationId = 001;

        mNM =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNM.notify(mNotificationId, mBuilder.build());

        stopSelf();

    }





}
