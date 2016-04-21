package ppla01.foodo;

/**
 * Created by Gema Raditya on 4/11/2016.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class AlarmTask implements Runnable {
    // The date selected for the alarm
    private final Calendar date;

    private final int code;
    // The android system alarm manager
    private final AlarmManager am;
    // Your context to retrieve the alarm manager from
    private final Context context;

    public AlarmTask(Context context, Calendar date, int code) {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.date = date;
        this.code = code;
    }

    @Override
    public void run() {
        // Request to start are service when the alarm date is upon us
        // We don't start an activity as we just want to pop up a notification into the system bar not a full activity
        Intent intent = new Intent(context, NotifyService.class);
        intent.putExtra(NotifyService.INTENT_NOTIFY, true);
        PendingIntent pendingIntent = PendingIntent.getService(context, code, intent, 0);

        // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
        am.setRepeating(AlarmManager.RTC, date.getTimeInMillis(),24*60*60*1000, pendingIntent);
    }
}
