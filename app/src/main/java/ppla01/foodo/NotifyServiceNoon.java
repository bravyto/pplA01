package ppla01.foodo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Gema Raditya on 5/14/2016.
 */
public class NotifyServiceNoon extends Service {
    Intent intentNoon;
    String message;
    String messageNoon;
    SharedPreferences sprefNoon;
    SharedPreferences.Editor editorNoon;
    AddFoodActivity foodNoon;
    /**
     * Class for clients to access
     */
    public class ServiceBinderNoon extends Binder {
        NotifyServiceNoon getService() {
            return NotifyServiceNoon.this;
        }
    }



    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "com.blundell.tut.service.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNMNoon;

    @Override
    public void onCreate() {
        //Log.i("NotifyService", "onCreate()");
        //Log.i("NotifyService", "weleeeh"+ AlarmTask.code);

        mNMNoon = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(AlarmTask.code == 3) {
            sprefNoon = getApplicationContext().getSharedPreferences("my_data", 0);
            foodNoon = new AddFoodActivity();
            editorNoon = sprefNoon.edit();
            messageNoon = "Reset data";
            Toast.makeText(NotifyServiceNoon.this, "Notification reset: " + sprefNoon.getFloat("kalori",0), Toast.LENGTH_LONG).show();
            editorNoon.putStringSet("SetSiang", null);
            editorNoon.putStringSet("SetPagi", null);
            editorNoon.putStringSet("SetMalam", null);
            editorNoon.commit();
            foodNoon.setNull();
        }


    }

    @Override
    public int onStartCommand(Intent intentNoon, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intentNoon);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if(intentNoon.getBooleanExtra(INTENT_NOTIFY, false))
            showNotificationNoon();

        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderNoon;
    }

    // This is the object that receives interactions from clients
    private final IBinder mBinderNoon = new ServiceBinderNoon();

    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotificationNoon() {

        NotificationCompat.Builder mBuilderNoon =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.food_icon2)
                        .setContentTitle("Let's eat")
                        .setContentText("It's time for Lunch.. Don't miss it!");

        int mNotificationId = 002;

        Intent contentIntent = new Intent(this, RecommendationActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(RecommendationActivity.class);
        stackBuilder.addNextIntent(contentIntent);



        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilderNoon.setContentIntent(resultPendingIntent);

        mBuilderNoon.setAutoCancel(true);

        mNMNoon =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNMNoon.notify(mNotificationId, mBuilderNoon.build());

        stopSelf();

    }
}
