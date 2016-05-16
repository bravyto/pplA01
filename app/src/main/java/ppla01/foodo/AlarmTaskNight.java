package ppla01.foodo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Gema Raditya on 5/15/2016.
 */
public class AlarmTaskNight implements Runnable {
    // The date selected for the alarm
    private final Calendar dateNight;

    private static int codeNight;
    // The android system alarm manager
    private final AlarmManager amNight;
    // Your context to retrieve the alarm manager from
    private final Context contextNight;



    //public NotifyService not = new NotifyService();



    public AlarmTaskNight(Context context, Calendar date, int code) {
        this.contextNight = context;
        this.amNight = (AlarmManager) contextNight.getSystemService(Context.ALARM_SERVICE);
        this.dateNight = date;
        this.codeNight = code;
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
        Intent intent = new Intent(contextNight, NotifyServiceNight.class);
        intent.putExtra(NotifyServiceNight.INTENT_NOTIFY, true);

        PendingIntent pendingIntent = PendingIntent.getService(contextNight, codeNight, intent, 0);

        // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
        amNight.setRepeating(AlarmManager.RTC, dateNight.getTimeInMillis(),24*60*60*1000, pendingIntent);
    }

}
