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
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bravyto on 10/04/2016.
 */
public class JadwalMakanActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_makan);

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

        set_eat_time = (Button) findViewById(R.id.submitEatTime);
        set_eat_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }
        });
    }
}