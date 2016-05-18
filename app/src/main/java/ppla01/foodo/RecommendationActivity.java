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
public class RecommendationActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_recommendation);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC424C")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Recommendation");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.action_favorite:
//                Intent intent = new Intent(RecommendationActivity.this, MainActivity.class);
//                startActivity(intent);
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
}