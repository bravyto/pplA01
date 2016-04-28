package ppla01.foodo;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Bravyto on 10/04/2016.
 */
public class JadwalMakanActivity extends AppCompatActivity {
//<<<<<<< HEAD

    protected Button set_eat_time;
    protected LinearLayout edit_breakfast;
    protected LinearLayout edit_lunch;
    protected LinearLayout edit_dinner;

    TextView breakfast_time;
    TextView lunch_time;
    TextView dinner_time;
    SharedPreferences spref;
    SharedPreferences.Editor editor;
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

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB9672")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Edit Eat Reminder");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        // Get a reference to our date picker
        //picker = (DatePicker) findViewById(R.id.scheduleTimePicker);

        back_profile = (Button) findViewById(R.id.submitEatTime);

        back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = spref.edit();
                editor.putString("log", "1");
                editor.commit();
                Intent i = new Intent(JadwalMakanActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        breakfast_time= (TextView) findViewById(R.id.breakfast);
        lunch_time= (TextView) findViewById(R.id.lunch);
        dinner_time= (TextView) findViewById(R.id.dinner);

        if(spref.getString("pagi","") == "") {
            editor.putString("pagi", "07:00");
            editor.commit();
        }
        breakfast_time.setText(spref.getString("pagi", ""));


        if(spref.getString("siang","") == "") {
            editor.putString("siang", "13:00");
            editor.commit();
        }

        lunch_time.setText(spref.getString("siang",""));

        if(spref.getString("malam","") == "") {
            editor.putString("malam", "18:00");
            editor.commit();
        }

        dinner_time.setText(spref.getString("malam",""));

    }

    public void setBreakfast(View v) {
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
            return new TimePickerDialog(JadwalMakanActivity.this, morningTimePickerListener,hour_morning,minute_morning,true);
        }
        else if(id==DIALOG_ID_NOON) {
            return new TimePickerDialog(JadwalMakanActivity.this, noonTimePickerListener,hour_noon,minute_noon,true);
        }
        else {
            return new TimePickerDialog(JadwalMakanActivity.this, nightTimePickerListener,hour_night,minute_night,true);
        }
    }

    protected TimePickerDialog.OnTimeSetListener morningTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_morning=hourOfDay;
            minute_morning=minute;

            int day2 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int month2= Calendar.getInstance().get(Calendar.MONTH);
            int year2 =  Calendar.getInstance().get(Calendar.YEAR);
            // Create a new calendar set to the date chosen
            // we set the time to midnight (i.e. the first minute of that day)
            Calendar c = Calendar.getInstance();
            c.set(year2,month2,day2 );
            c.set(Calendar.HOUR_OF_DAY, hour_morning);
            c.set(Calendar.MINUTE, minute_morning);
            c.set(Calendar.SECOND, 0);

            editor = spref.edit();
            String hour = "" + hour_morning;
            String minutes = ""+minute_morning;
            String jampagi = "";
            if(hour_morning<10) {
                hour = "0" + hour;
                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                jampagi = hour + ":" + minutes;
            }
            else {
                if(minutes.length()==1){
                    minutes="0"+minutes;
                }
                jampagi = hour + ":"+  minutes;
            }
            editor.putString("pagi", jampagi);
            editor.commit();

            breakfast_time.setText(jampagi);

            // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
            scheduleClient.setAlarmForNotification(c,0);
            // scheduleClient.setAlarmForNotification(d,1);


            Toast.makeText(JadwalMakanActivity.this,"Notification set for: " +jampagi , Toast.LENGTH_LONG).show();
        }
    };

    protected TimePickerDialog.OnTimeSetListener noonTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_noon=hourOfDay;
            minute_noon=minute;

            int day2 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int month2= Calendar.getInstance().get(Calendar.MONTH);
            int year2 =  Calendar.getInstance().get(Calendar.YEAR);
            // Create a new calendar set to the date chosen
            // we set the time to midnight (i.e. the first minute of that day)
            Calendar d = Calendar.getInstance();
            d.set(year2,month2,day2 );
            d.set(Calendar.HOUR_OF_DAY, hour_noon);
            d.set(Calendar.MINUTE, minute_noon);
            d.set(Calendar.SECOND, 0);

            editor = spref.edit();
            String hour = ""+hour_noon;
            String minutes = ""+minute_noon;
            String jamsiang = "";
            if(hour_noon<10) {
                hour = "0" + hour;
                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                jamsiang = hour + ":" + minutes;
            } else {
                if(minutes.length()==1){
                    minutes="0"+minutes;
                }
                    jamsiang = hour + ":"+  minutes;
            }
            editor.putString("siang",jamsiang);
            editor.commit();

            lunch_time.setText(jamsiang);


            // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
            scheduleClient.setAlarmForNotification(d,1);
            // scheduleClient.setAlarmForNotification(d,1);


            Toast.makeText(JadwalMakanActivity.this,"Notification set for: " + jamsiang, Toast.LENGTH_LONG).show();
        }
    };

    protected TimePickerDialog.OnTimeSetListener nightTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_night=hourOfDay;
            minute_night=minute;

            int day2 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int month2= Calendar.getInstance().get(Calendar.MONTH);
            int year2 =  Calendar.getInstance().get(Calendar.YEAR);
            // Create a new calendar set to the date chosen
            // we set the time to midnight (i.e. the first minute of that day)
            Calendar e = Calendar.getInstance();
            e.set(year2,month2,day2 );
            e.set(Calendar.HOUR_OF_DAY, hour_night);
            e.set(Calendar.MINUTE, minute_night);
            e.set(Calendar.SECOND, 0);

            editor = spref.edit();
            String hour = ""+hour_night;
            String minutes = ""+minute_night;
            String jammalam = "";
            if(hour_night<10) {
                hour = "0" + hour;
                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                jammalam = hour + ":" + minutes;
            }
            else {
                if(minutes.length()==1){
                    minutes="0"+minutes;
                }
                jammalam = hour + ":"+  minutes;
            }
            editor.putString("malam",jammalam);
            editor.commit();

            dinner_time.setText(jammalam);


            // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
            scheduleClient.setAlarmForNotification(e,2);
            // scheduleClient.setAlarmForNotification(d,1);


            Toast.makeText(JadwalMakanActivity.this,"Notification set for: " + jammalam, Toast.LENGTH_LONG).show();
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
