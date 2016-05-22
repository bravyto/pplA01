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
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class NotifyService extends Service{
    Intent intent;
    String message;
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    AddFoodActivity food;
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

        if(AlarmTask.code == 3) {
            spref = getApplicationContext().getSharedPreferences("my_data", 0);
            food = new AddFoodActivity();
            editor = spref.edit();
            message = "Reset data";
            Toast.makeText(NotifyService.this, "Notification reset: " + spref.getFloat("kalori",0), Toast.LENGTH_LONG).show();
            editor.putStringSet("SetSiang", null);
            editor.putStringSet("SetPagi", null);
            editor.putStringSet("SetMalam", null);
            editor.commit();
            food.setNull();
        }


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

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.food_icon2)
                        .setContentTitle("Let's eat")
                        .setContentText("guys Good morning, It's time to take your Breakfast :)");

        int mNotificationId = 001;


        Intent contentIntent = new Intent(this, RecommendationActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(RecommendationActivity.class);
        stackBuilder.addNextIntent(contentIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        mBuilder.setAutoCancel(true);

        mNM =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNM.notify(mNotificationId, mBuilder.build());

        stopSelf();

    }





}
