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
        actionBar.setHomeButtonEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB9672")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Profile Info");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);


        tinggi = spref.getString("tinggi", "");
        nama = spref.getString("nama", "");
        umur = spref.getString("umur", "");
        beratnow = spref.getString("beratnow", "");
        gender = spref.getString("gender", "");
        aktivitasnya=spref.getString("Aktivitas","");

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
            case android.R.id.home:
                System.out.println("Pril");
                //Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                finish();
                return true;

            case R.id.action_favorite:
                editor = spref.edit();
                editor.putString("fromHome", "1");
                editor.putString("log", "");

                editor.commit();
                Intent intent2 = new Intent(ProfileActivity.this, MainActivity.class);
                finish();
                startActivity(intent2);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
