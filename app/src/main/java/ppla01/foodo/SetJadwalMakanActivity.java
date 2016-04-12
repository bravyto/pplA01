package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bravyto on 10/04/2016.
 */
public class SetJadwalMakanActivity extends AppCompatActivity {

    protected Button set_eat_time;
    protected LinearLayout edit_breakfast;
    String user_name="";
    String user_birthdate="";
    String user_weight="";
    String user_target="";
    String user_height="";
    String user_gender="";
    String user_time="";
    String user_breakfast;
    String user_lunch;
    String user_dinner;
    String flag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_jadwal_makan);

        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        user_birthdate = intent.getStringExtra("user_birthdate");
        user_weight = intent.getStringExtra("user_weight");
        user_target = intent.getStringExtra("user_target");
        user_height = intent.getStringExtra("user_height");
        user_gender = intent.getStringExtra("user_gender");
        if(intent.hasExtra("user_breakfast")){
            user_time = intent.getStringExtra("user_breakfast");
            user_lunch = intent.getStringExtra("lunch");
            user_dinner = intent.getStringExtra("dinner");
            flag="b";
        }
        else if(intent.hasExtra("user_lunch")) {
            user_time = intent.getStringExtra("user_lunch");
            user_breakfast = intent.getStringExtra("breakfast");
            user_dinner = intent.getStringExtra("dinner");
            flag="l";
        }

        else if(intent.hasExtra("user_dinner")) {
            user_time = intent.getStringExtra("user_dinner");
            user_lunch = intent.getStringExtra("lunch");
            user_breakfast = intent.getStringExtra("breakfast");
            flag="d";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
        Date date = null;
        try {
            date = sdf.parse(user_time);
        } catch (ParseException e) {
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

       final TimePicker picker = (TimePicker) findViewById(R.id.eatTime);
        picker.setIs24HourView(true);
        picker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        picker.setCurrentMinute(c.get(Calendar.MINUTE));


        set_eat_time = (Button) findViewById(R.id.submitEatTime);
        set_eat_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(picker.getCurrentHour()<10){
                    if(picker.getCurrentMinute()<10){
                        user_time = "0"+picker.getCurrentHour()+":0"+picker.getCurrentMinute();
                    }
                    else{
                        user_time = "0"+picker.getCurrentHour()+":"+picker.getCurrentMinute();
                    }
                }
                else{
                    if(picker.getCurrentMinute()<10){
                        user_time = picker.getCurrentHour()+":0"+picker.getCurrentMinute();
                    }
                    else{
                        user_time = picker.getCurrentHour()+":"+picker.getCurrentMinute();
                    }
                }

                Intent i = new Intent(SetJadwalMakanActivity.this, JadwalMakanActivity.class);
                i.putExtra("user_name",user_name);
                i.putExtra("user_birthdate",user_birthdate);
                i.putExtra("user_weight",user_weight);
                i.putExtra("user_target",user_target);
                i.putExtra("user_height",user_height);
                i.putExtra("user_gender",user_gender);
                if(flag.equals("b")) {
                    i.putExtra("user_breakfast", user_time);
                    i.putExtra("user_lunch",user_lunch);
                    i.putExtra("user_dinner",user_dinner);
                }
                else if(flag.equals("l")) {
                    i.putExtra("user_lunch", user_time);
                    i.putExtra("user_breakfast",user_breakfast);
                    i.putExtra("user_dinner",user_dinner);
                    System.out.println(flag);
                }
                else if(flag.equals("d")){
                    i.putExtra("user_dinner",user_time);
                    i.putExtra("user_lunch",user_lunch);
                    i.putExtra("user_breakfast",user_breakfast);
                    System.out.println(flag);
                }
                i.putExtra("flag",flag);
                SetJadwalMakanActivity.this.finish();
                startActivity(i);
            }
        });
    }
}
