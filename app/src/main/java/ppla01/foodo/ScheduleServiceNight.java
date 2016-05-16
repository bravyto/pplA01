package ppla01.foodo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Gema Raditya on 5/15/2016.
 */
public class ScheduleServiceNight extends Service {
    /**
     * Class for clients to access
     */
    public class ServiceBinderNight extends Binder {
        ScheduleServiceNight getService() {
            return ScheduleServiceNight.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ScheduleService", "Received start id " + startId + ": " + intent);

        // We want this service to continue running until it is explicitly stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderNight;
    }

    // This is the object that receives interactions from clients. See
    private final IBinder mBinderNight = new ServiceBinderNight();

    /**
     * Show an alarm for a certain date when the alarm is called it will pop up a notification
     */
    public void setAlarmNight(Calendar c, int code) {
        // This starts a new thread to set the alarm
        // You want to push off your tasks onto a new thread to free up the UI to carry on responding
        new AlarmTaskNight(this, c, code).run();
    }

}
