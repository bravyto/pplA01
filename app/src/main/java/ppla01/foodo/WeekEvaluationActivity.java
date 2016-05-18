package ppla01.foodo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Bravyto on 14/05/2016.
 */
public class WeekEvaluationActivity extends AppCompatActivity {

    SharedPreferences spref;
    SharedPreferences.Editor editor;

    protected EditText user_weight;
    protected EditText user_height;

    protected Button submit_evaluation;

    boolean valid = true ;
    String  tinggi, beratnow;
    String [] separate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_evaluation);

        scheduleAlarm(findViewById(android.R.id.content));

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC424C")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setTitle("Week Evaluation");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        user_weight = (EditText) findViewById(R.id.weight);
        user_height = (EditText) findViewById(R.id.height);

        user_height.setText(spref.getString("tinggi", ""), null);
        user_weight.setText(spref.getString("beratnow", ""), null);

        submit_evaluation = (Button) findViewById(R.id.submitEvaluation);
        submit_evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinggi = user_height.getText().toString();
                beratnow = user_weight.getText().toString();
                if (tinggi.equals("")) {
                    user_height.setError("Type your height in cm");
                    valid = false;
                }
                if (beratnow.equals("")) {
                    user_weight.setError("Type your weight in kg");
                    valid = false;
                }
                if (valid) {

                    editor.putString("tinggi", tinggi);
                    editor.putString("beratnow", beratnow);
                    Float massa = spref.getFloat("Aktivity",0);
                    double berat = 0.0;
                    if(!spref.getString("beratnow", "").equals(""))
                        berat = Double.parseDouble(spref.getString("beratnow", ""));
                    double tinggi = 0.0;
                    if(!spref.getString("beratnow", "").equals(""))
                        tinggi = Double.parseDouble(spref.getString("tinggi", ""));
                    int curent = Calendar.getInstance().get(Calendar.YEAR);
                    separate = spref.getString("umur", "").split("/");
                    double BMR=0;
                    String gen = spref.getString("gender", "");

                    if(gen.equals("Pria")){
                        BMR = 66.473 + (13.7516 * berat) + (5 * tinggi) - (6.755 * (curent-Double.parseDouble(separate[2])) ) *  massa;
                    }
                    else{
                        BMR = 655.095 + (9.5634 * berat) + (1.8496 * tinggi ) - (4.6756 * (curent - Double.parseDouble(separate[2])) * massa) ;
                    }
                    editor.putFloat("BMR", (float) BMR);
                    editor.putString("log", "1");
                    editor.commit();

                    Intent intent = new Intent(v.getContext(), Main2Activity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    public void scheduleAlarm(View V)
    {
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
        Long time = new GregorianCalendar().getTimeInMillis()+7*24*60*60*1000;

        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers and
        //we will write the code to send SMS inside onRecieve() method pf Alarmreciever class
        Intent intentAlarm = new Intent(this, AlarmReciever.class);

        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

    }
}
