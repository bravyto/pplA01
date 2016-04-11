package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bravyto on 10/04/2016.
 */
public class JadwalMakanActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_makan);

        SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
        Date date = null;
        try {
            date = sdf.parse("07:00");
        } catch (ParseException e) {
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        TimePicker picker = (TimePicker) findViewById(R.id.breakfast);
        picker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        picker.setCurrentMinute(c.get(Calendar.MINUTE));

        try {
            date = sdf.parse("12:30");
        } catch (ParseException e) {
        }
        c.setTime(date);

        TimePicker picker2 = (TimePicker) findViewById(R.id.lunch);
        picker2.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        picker2.setCurrentMinute(c.get(Calendar.MINUTE));

        try {
            date = sdf.parse("18:00");
        } catch (ParseException e) {
        }
        c.setTime(date);

        TimePicker picker3 = (TimePicker) findViewById(R.id.dinner);
        picker3.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        picker3.setCurrentMinute(c.get(Calendar.MINUTE));
    }
}
