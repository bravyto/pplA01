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
 * Created by Gema Raditya on 5/15/2016.
 */
public class NotifyServiceNight extends Service {
    Intent intentNight;
    String message;
    String messageNight;
    SharedPreferences sprefNight;
    SharedPreferences.Editor editorNight;
    AddFoodActivity foodNight;
    /**
     * Class for clients to access
     */
    public class ServiceBinderNight extends Binder {
        NotifyServiceNight getService() {
            return NotifyServiceNight.this;
        }
    }



    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "com.blundell.tut.service.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNMNight;

    @Override
    public void onCreate() {
        //Log.i("NotifyService", "onCreate()");
        //Log.i("NotifyService", "weleeeh"+ AlarmTask.code);

        mNMNight = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(AlarmTask.code == 3) {
            sprefNight = getApplicationContext().getSharedPreferences("my_data", 0);
            foodNight = new AddFoodActivity();
            editorNight = sprefNight.edit();
            messageNight = "Reset data";
            Toast.makeText(NotifyServiceNight.this, "Notification reset: " + sprefNight.getFloat("kalori",0), Toast.LENGTH_LONG).show();
            editorNight.putStringSet("SetSiang", null);
            editorNight.putStringSet("SetPagi", null);
            editorNight.putStringSet("SetMalam", null);
            editorNight.commit();
            foodNight.setNull();
        }


    }

    @Override
    public int onStartCommand(Intent intentNight, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intentNight);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if(intentNight.getBooleanExtra(INTENT_NOTIFY, false))
            showNotificationNight();

        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderNight;
    }

    // This is the object that receives interactions from clients
    private final IBinder mBinderNight = new ServiceBinderNight();

    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotificationNight() {

        NotificationCompat.Builder mBuilderNight =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.food_icon2)
                        .setContentTitle("Let's eat")
                        .setContentText("Let's go for dinner!");

        int mNotificationId = 002;

        Intent contentIntent = new Intent(this, RecommendationActivity.class);
        contentIntent.putExtra("deMalam", "malamgan");
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(RecommendationActivity.class);
        stackBuilder.addNextIntent(contentIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilderNight.setContentIntent(resultPendingIntent);

        mBuilderNight.setAutoCancel(true);

        mNMNight =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNMNight.notify(mNotificationId, mBuilderNight.build());

        stopSelf();

    }

}
