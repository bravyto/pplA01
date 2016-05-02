package ppla01.foodo;

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
import android.widget.TextView;

/**
 * Created by Bravyto on 29/04/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    protected Button edit_profile;
    protected Button entry_food;
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
    //=======
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    String  nama, tinggi, umur, beratnow, beratThen,  gender,aktivitasnya;
//>>>>>>> refs/remotes/origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Profile Info");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();


//        Intent intent = getIntent();
//        user_name = intent.getStringExtra("user_name");
//        user_birthdate = intent.getStringExtra("user_birthdate");
//        user_weight = intent.getStringExtra("user_weight");
//        user_target = intent.getStringExtra("user_target");
//        user_height = intent.getStringExtra("user_height");
//        user_gender = intent.getStringExtra("user_gender");
//        update_name = (TextView) findViewById(R.id.name);
//        update_name.setText(user_name);
//        update_birthdate = (TextView) findViewById(R.id.birthdate);
//        update_birthdate.setText(user_birthdate);
//        update_weight = (TextView) findViewById(R.id.weight);
//        update_weight.setText(user_weight);
//        update_weight.append(" kg");
//        update_target = (TextView) findViewById(R.id.target);
//        update_target.setText(user_target);
//        update_target.append(" kg");
//        update_height = (TextView) findViewById(R.id.height);
//        update_height.setText(user_height);
//        update_height.append(" cm");
//        update_gender = (TextView) findViewById(R.id.gender);
//        update_gender.setText(user_gender);
        tinggi = spref.getString("tinggi", "");
        nama = spref.getString("nama", "");
        umur = spref.getString("umur", "");
        beratnow = spref.getString("beratnow", "");
        gender = spref.getString("gender", "");
        aktivitasnya=spref.getString("Activity","");

        TextView tinggiv = (TextView)findViewById(R.id.height);
        tinggiv.setText(tinggi + " cm");

        TextView namav = (TextView) findViewById(R.id.name);
        namav.setText(nama);

        TextView umurv = (TextView) findViewById(R.id.birthdate);
        umurv.setText(umur);

        TextView beratnowv = (TextView) findViewById(R.id.weight);
        beratnowv.setText(beratnow + " kg");

        TextView genderv = (TextView) findViewById(R.id.gender);
        genderv.setText(gender);

        TextView aktivitas= (TextView) findViewById(R.id.activity);
        aktivitas.setText(aktivitasnya);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
