package ppla01.foodo;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bravyto on 10/04/2016.
 */
public class JadwalMakanActivity extends AppCompatActivity {

    // This is a handle so that we can call methods on our service
    private ScheduleClient scheduleClient;
    // This is the date picker used to select the date for our notification
    // private DatePicker picker;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    protected Button back_profile;
    static final int DIALOG_ID=0;
    static final int DIALOG_ID_NOON=1;
    static final int DIALOG_ID_NIGHT=2;
    int hour_morning=7;
    int minute_morning=0;
    int hour_noon=13;
    int minute_noon=0;
    int hour_night=18;
    int minute_night=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_makan);

        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        // Get a reference to our date picker
        //picker = (DatePicker) findViewById(R.id.scheduleTimePicker);

        back_profile = (Button) findViewById(R.id.backToProfile);

        back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(JadwalMakanActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });


    }

    public void onDateSelectedButtonClick(View v) {

        showDialog(DIALOG_ID);


    }

    public void setLunch(View v) {
        showDialog(DIALOG_ID_NOON);
    }
    public void setDinner(View v) {
        showDialog(DIALOG_ID_NIGHT);
    }


    protected Dialog onCreateDialog(int id) {
        if(id==DIALOG_ID) {
            return new TimePickerDialog(JadwalMakanActivity.this, kTimePickerListener,hour_morning,minute_morning,false);
        }
        else if(id==DIALOG_ID_NOON) {
            return new TimePickerDialog(JadwalMakanActivity.this, noonTimePickerListener,hour_noon,minute_noon,false);
        }
        else {
            return new TimePickerDialog(JadwalMakanActivity.this, nightTimePickerListener,hour_night,minute_night,false);
        }
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_morning=hourOfDay;
            minute_morning=minute;

            int day2 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int month2= Calendar.getInstance().get(Calendar.MONTH);
            int year2 =  Calendar.getInstance().get(Calendar.YEAR);;
            // Create a new calendar set to the date chosen
            // we set the time to midnight (i.e. the first minute of that day)
            Calendar c = Calendar.getInstance();
            c.set(year2,month2,day2 );
            c.set(Calendar.HOUR_OF_DAY, hour_morning);
            c.set(Calendar.MINUTE, minute_morning);
            c.set(Calendar.SECOND, 0);


            // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
            scheduleClient.setAlarmForNotification(c,0);
            // scheduleClient.setAlarmForNotification(d,1);


            Toast.makeText(JadwalMakanActivity.this,"Notification set fors: " + hour_morning+ " : " + minute_morning, Toast.LENGTH_LONG).show();
        }
    };

    protected TimePickerDialog.OnTimeSetListener noonTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_noon=hourOfDay;
            minute_noon=minute;

            int day2 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int month2= Calendar.getInstance().get(Calendar.MONTH);
            int year2 =  Calendar.getInstance().get(Calendar.YEAR);;
            // Create a new calendar set to the date chosen
            // we set the time to midnight (i.e. the first minute of that day)
            Calendar d = Calendar.getInstance();
            d.set(year2,month2,day2 );
            d.set(Calendar.HOUR_OF_DAY, hour_noon);
            d.set(Calendar.MINUTE, minute_noon);
            d.set(Calendar.SECOND, 0);


            // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
            scheduleClient.setAlarmForNotification(d,1);
            // scheduleClient.setAlarmForNotification(d,1);


            Toast.makeText(JadwalMakanActivity.this,"Notification set for: " + hour_noon+ " : " + minute_noon, Toast.LENGTH_LONG).show();
        }
    };

    protected TimePickerDialog.OnTimeSetListener nightTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_night=hourOfDay;
            minute_night=minute;

            int day2 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int month2= Calendar.getInstance().get(Calendar.MONTH);
            int year2 =  Calendar.getInstance().get(Calendar.YEAR);;
            // Create a new calendar set to the date chosen
            // we set the time to midnight (i.e. the first minute of that day)
            Calendar e = Calendar.getInstance();
            e.set(year2,month2,day2 );
            e.set(Calendar.HOUR_OF_DAY, hour_night);
            e.set(Calendar.MINUTE, minute_night);
            e.set(Calendar.SECOND, 0);


            // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
            scheduleClient.setAlarmForNotification(e,2);
            // scheduleClient.setAlarmForNotification(d,1);


            Toast.makeText(JadwalMakanActivity.this,"Notification set for: " + hour_night+ " : " + minute_night, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        if(scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();

    }
}
