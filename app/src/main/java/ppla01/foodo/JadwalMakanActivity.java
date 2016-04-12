package ppla01.foodo;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Color;
=======
import android.content.SharedPreferences;
>>>>>>> refs/remotes/origin/master
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
<<<<<<< HEAD

    protected Button set_eat_time;
    protected LinearLayout edit_breakfast;
    protected LinearLayout edit_lunch;
    protected LinearLayout edit_dinner;
    String user_name="";
    String user_birthdate="";
    String user_weight="";
    String user_target="";
    String user_height="";
    String user_gender="";
    String user_breakfast;
    String user_lunch;
    String user_dinner;
    TextView breakfast_time;
    TextView lunch_time;
    TextView dinner_time;
=======
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

>>>>>>> refs/remotes/origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_makan);
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        // Get a reference to our date picker
        //picker = (DatePicker) findViewById(R.id.scheduleTimePicker);

        back_profile = (Button) findViewById(R.id.backToProfile);

        back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = spref.edit();
                editor.putString("log", "1");
                editor.commit();
                Intent i = new Intent(JadwalMakanActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });


<<<<<<< HEAD
        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        user_birthdate = intent.getStringExtra("user_birthdate");
        user_weight = intent.getStringExtra("user_weight");
        user_target = intent.getStringExtra("user_target");
        user_height = intent.getStringExtra("user_height");
        user_gender = intent.getStringExtra("user_gender");

        if(intent.hasExtra("flag")) {
            user_breakfast = intent.getStringExtra("user_breakfast");
            user_lunch= intent.getStringExtra("user_lunch");
            user_dinner = intent.getStringExtra("user_dinner");
        }
        else{
            user_breakfast="06:00";
            user_lunch="12:00";
            user_dinner="18:00";
        }

        breakfast_time= (TextView) findViewById(R.id.breakfast);
        breakfast_time.setText(user_breakfast);

        lunch_time= (TextView) findViewById(R.id.lunch);
        lunch_time.setText(user_lunch);

        dinner_time= (TextView) findViewById(R.id.dinner);
        dinner_time.setText(user_dinner);

        edit_breakfast = (LinearLayout) findViewById(R.id.breakfastClick);
        edit_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(JadwalMakanActivity.this, SetJadwalMakanActivity.class);
                in.putExtra("user_name", user_name);
                in.putExtra("user_birthdate", user_birthdate);
                in.putExtra("user_weight", user_weight);
                in.putExtra("user_target", user_target);
                in.putExtra("user_height", user_height);
                in.putExtra("user_gender", user_gender);
                in.putExtra("user_breakfast", user_breakfast);
                in.putExtra("lunch", user_lunch);
                in.putExtra("dinner", user_dinner);
                JadwalMakanActivity.this.finish();
                startActivity(in);
            }
        });

        edit_lunch = (LinearLayout) findViewById(R.id.lunchClick);
        edit_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(JadwalMakanActivity.this, SetJadwalMakanActivity.class);
                in.putExtra("user_name", user_name);
                in.putExtra("user_birthdate", user_birthdate);
                in.putExtra("user_weight", user_weight);
                in.putExtra("user_target", user_target);
                in.putExtra("user_height", user_height);
                in.putExtra("user_gender", user_gender);
                in.putExtra("user_lunch", user_lunch);
                in.putExtra("breakfast", user_breakfast);
                in.putExtra("dinner", user_dinner);
                JadwalMakanActivity.this.finish();
                startActivity(in);
            }
        });

        edit_dinner = (LinearLayout) findViewById(R.id.dinnerClick);
        edit_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(JadwalMakanActivity.this, SetJadwalMakanActivity.class);
                in.putExtra("user_name", user_name);
                in.putExtra("user_birthdate", user_birthdate);
                in.putExtra("user_weight", user_weight);
                in.putExtra("user_target", user_target);
                in.putExtra("user_height", user_height);
                in.putExtra("user_gender", user_gender);
                in.putExtra("user_dinner", user_dinner);
                in.putExtra("lunch", user_lunch);
                in.putExtra("breakfast", user_breakfast);
                JadwalMakanActivity.this.finish();
                startActivity(in);
            }
        });
=======
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

            editor = spref.edit();
            String hour = "" + hour_morning;
            String minutes = ""+minute_morning;
            String jampagi = "";
            if(hour_morning<12) {
                if (hour_morning != 10 && hour_morning != 11) {
                    hour = "0" + hour;
                }
                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                jampagi = hour + ":" + minutes + " a.m";
            } else {
                if(minutes.length()==1){
                    minutes="0"+minutes;
                }
                String temp = ""+hour_morning%12;
                if (temp.length()==1){
                    jampagi = "0" + temp + ":"+  minutes+ " p.m";
                } else{
                    jampagi = temp + ":"+  minutes+ " p.m";
                }
            }
            editor.putString("pagi",jampagi);
            editor.commit();

            // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
            scheduleClient.setAlarmForNotification(c,0);
            // scheduleClient.setAlarmForNotification(d,1);


            Toast.makeText(JadwalMakanActivity.this,"Notification set for: " + hour_morning+ " : " + minute_morning, Toast.LENGTH_LONG).show();
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

            editor = spref.edit();
            String hour = ""+hour_noon;
            String minutes = ""+minute_noon;
            String jamsiang = "";
            if(hour_noon<12) {
                if (hour_noon != 10 && hour_noon != 11) {
                    hour = "0" + hour;
                }
                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                jamsiang = hour + ":" + minutes + " a.m";
            } else {
                if(minutes.length()==1){
                    minutes="0"+minutes;
                }
                String temp = ""+hour_noon%12;
                if (temp.length()==1){
                    jamsiang = "0" + temp + ":"+  minutes+ " p.m";
                } else{
                    jamsiang = temp + ":"+  minutes+ " p.m";
                }
            }
            editor.putString("siang",jamsiang);
            editor.commit();

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
>>>>>>> refs/remotes/origin/master

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

<<<<<<< HEAD
                Intent i = new Intent(JadwalMakanActivity.this, MenuActivity.class);
                i.putExtra("user_name",user_name);
                i.putExtra("user_birthdate",user_birthdate);
                i.putExtra("user_weight",user_weight);
                i.putExtra("user_target",user_target);
                i.putExtra("user_height",user_height);
                i.putExtra("user_gender",user_gender);
                i.putExtra("user_breakfast",user_breakfast);
                i.putExtra("user_lunch", user_lunch);
                i.putExtra("user_dinner",user_dinner);
                startActivity(i);
=======
            editor = spref.edit();
            String hour = ""+hour_night;
            String minutes = ""+minute_night;
            String jammalam = "";
            if(hour_night<12) {
                if (hour_night != 10 && hour_night != 11) {
                    hour = "0" + hour;
                }
                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                jammalam = hour + ":" + minutes + " a.m";
            } else {
                if(minutes.length()==1){
                    minutes="0"+minutes;
                }
                String temp = ""+hour_night%12;
                if (temp.length()==1){
                    jammalam = "0" + temp + ":"+  minutes+ " p.m";
                } else{
                    jammalam = temp + ":"+  minutes+ " p.m";
                }
>>>>>>> refs/remotes/origin/master
            }
            editor.putString("malam",jammalam);
            editor.commit();

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
<<<<<<< HEAD
}
=======

}
>>>>>>> refs/remotes/origin/master
