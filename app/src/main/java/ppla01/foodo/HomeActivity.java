package ppla01.foodo;

import android.app.Activity;
import android.app.FragmentContainer;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by TOSHIBA on 10/04/2016.
 */
public class HomeActivity extends AppCompatActivity {
    //<<<<<<< HEAD
    String user_name="";
    String user_birthdate="";
    String user_weight="";
    String user_target="";
    String user_height="";
    String user_gender="";
    TextView update_name;
    TextView update_birthdate;
    TextView update_weight;
    TextView update_target;
    TextView update_height;
    TextView update_gender;
    protected Button button1;
    protected Button button2;
    protected Button button3;
    protected Button button4;
    //=======
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    String  nama, tinggi, umur, beratnow, beratThen,  gender,morning,noon,evening;
//>>>>>>> refs/remotes/origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB9672")));

        setTitle("FooDo");

//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        HomeFragment homeFragment = new HomeFragment();
//        fragmentTransaction.add(R.id.fragment_container, homeFragment);
//        fragmentTransaction.commit();
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
//        kalori = (TextView) view.findViewById(R.id.tampil);
//        kalori.setText(spref.getString("Calori", ""), null);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                ProfileFragment profileFragment = new ProfileFragment();
//                fragmentTransaction.replace(R.id.fragment_container,profileFragment);
//                fragmentTransaction.commit();

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                SetAlarmFragment setAlarmFragment = new SetAlarmFragment();
//                fragmentTransaction.replace(R.id.fragment_container,setAlarmFragment);
//                fragmentTransaction.commit();
                Intent i = new Intent(HomeActivity.this, JadwalMakanActivity.class);
                startActivity(i);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                SetAlarmFragment setAlarmFragment = new SetAlarmFragment();
//                fragmentTransaction.replace(R.id.fragment_container,setAlarmFragment);
//                fragmentTransaction.commit();
                Intent i = new Intent(HomeActivity.this, FoodActivity.class);
                startActivity(i);
            }
        });





//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    protected void onStart(){
        super.onStart();
        if (!spref.getString("log","").equals("1")){
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
