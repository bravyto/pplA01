package ppla01.foodo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Gema Raditya on 5/14/2016.
 */
public class AlarmTaskNoon implements Runnable {
    // The date selected for the alarm
    private final Calendar dateNoon;

    private static int codeNoon;
    // The android system alarm manager
    private final AlarmManager amNoon;
    // Your context to retrieve the alarm manager from
    private final Context contextNoon;



    //public NotifyService not = new NotifyService();



    public AlarmTaskNoon(Context context, Calendar date, int code) {
        this.contextNoon = context;
        this.amNoon = (AlarmManager) contextNoon.getSystemService(Context.ALARM_SERVICE);
        this.dateNoon = date;
        this.codeNoon = code;
    }

/*
    public void send() {
        not.recieve(code);

    }
    */

    @Override
    public void run() {
        //send();
        // Request to start are service when the alarm date is upon us
        // We don't start an activity as we just want to pop up a notification into the system bar not a full activity
        Intent intent = new Intent(contextNoon, NotifyServiceNoon.class);
        intent.putExtra(NotifyServiceNoon.INTENT_NOTIFY, true);

        PendingIntent pendingIntent = PendingIntent.getService(contextNoon, codeNoon, intent, 0);

        // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
        amNoon.setRepeating(AlarmManager.RTC, dateNoon.getTimeInMillis(),24*60*60*1000, pendingIntent);
    }

}
